package com.likes.modules.admin.pay;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.config.UdunProperties;
import com.likes.common.util.redis.RedisLock;
import com.likes.modules.admin.pay.service.MemWalletService;
import com.uduncloud.sdk.domain.Trade;
import com.uduncloud.sdk.util.UdunUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/udunBack")
public class CallBackController {

    Logger log = LoggerFactory.getLogger(CallBackController.class);

    @Resource
    UdunProperties udunProperties;
    @Resource
    MemWalletService memWalletService;
    @Resource
    private RedisTemplate redisTemplate;


    /**
     * 处理优盾网关回调信息，包括充币和提币
     *
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param body      消息内容
     * @param sign      签名
     * @return 返回参数
     */
    @AllowAccess
    @PostMapping("/notify")
    public String tradeCallback(@RequestParam("timestamp") String timestamp,
                                @RequestParam("nonce") String nonce,
                                @RequestParam("body") String body,
                                @RequestParam("sign") String sign) {
        log.info("timestamp:{},nonce:{},sign:{},body:{}", timestamp, nonce, sign, body);
        if (!UdunUtils.checkSign(udunProperties.getMerchantKey(), timestamp, nonce, body, sign)) {
            log.error("验签失败====={},key=={}",body,udunProperties.getMerchantKey());
            return "error";
        }
        Trade trade = JSONUtil.toBean(body, Trade.class);
        if (trade.getTradeType() == 1) {
            log.info("充币回调处理");
            if (!trade.getMainCoinType().equals("195")) {
                log.error("用户充值类型错误 ======》》{}", JSON.toJSONString(trade));
                return "error";
            }
            if (!trade.getCoinType().equals("TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t")) {
                log.error("用户充值类型错误 ======》》{}", JSON.toJSONString(trade));
                return "error";
            }
            memWalletService.udunCallBack(trade);
        } else if (trade.getTradeType() == 2) {
            String keySuffix = RedisLock.FINANCE_APP_WITHDRAWAL_UDUN + trade.getBusinessId();
            redisTemplate.opsForValue().setIfAbsent(keySuffix, "1", 10, TimeUnit.SECONDS);
            log.info("提币回调处理");
            if (trade.getStatus() == 1) {
                log.info("审核通过");
                memWalletService.modifyWithdrawStatus(trade);
            } else if (trade.getStatus() == 2) {
                log.info("审核不通过");
                memWalletService.modifyWithdrawStatus(trade);
            } else if (trade.getStatus() == 3) {
                memWalletService.withdrawArrived(trade);
                log.info("提币已到账");
            }else if (trade.getStatus() == 4) {
                memWalletService.withdrawArrived(trade);
                log.info("提币失败");
            }
        }
        return "success";
    }
}
