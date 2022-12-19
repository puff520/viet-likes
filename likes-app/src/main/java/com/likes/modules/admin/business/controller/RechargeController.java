package com.likes.modules.admin.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.enums.LoginUserTypeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.TraRechargemealRequest;
import com.likes.common.util.LogUtils;
import com.likes.common.util.StringUtils;
import com.likes.common.util.redis.RedisLock;
import com.likes.modules.admin.business.service.RechargeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 充值 线下 / 线上
 *
 * @author bjkj
 */
@Controller
@RequestMapping(value = "/recharge")
public class RechargeController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RechargeService rechargeService;
    @Resource
    private RedisTemplate redisTemplate;



    //获取支付信息
    @ResponseBody
    @RequestMapping(name = "获取收款信息", value = "/getBankList", method = RequestMethod.GET)
    public ResultInfo getBankList() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        LoginUser loginUserAPP = getLoginUserAPP();
        if (LoginUserTypeEnum.isTrailAccount(loginUserAPP.getLogintype())) {
            return ResultInfo.error("试玩会员无法发起提现充值");
        }
        try {

            response.setData(rechargeService.getBankList(this.getLoginUserAPP()));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("失败:{}", e.getMessage());
        } catch (Exception e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("getBankList error,req:{}", e);
        }
        logger.info("/getBankList耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "代理充值下单", value = "/v3/doAgentPay", method = RequestMethod.POST)
    public ResultInfo doPayV1(TraRechargemealRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        RedisLock lock = new RedisLock(RedisLock.FINANCE_APP_WITHDRAWAL_APPLY_LOCK, 0, 10 * 1000);
        try {
            if (null == req.getBankid() || StringUtils.isEmpty(req.getPayuser())) {
                return ResultInfo.paramsError();
            }
            LoginUser loginUserAPP = getLoginUserAPP();
            String keySuffix = RedisLock.FINANCE_APP_PAY_APPLY + loginUserAPP.getMemid();
            if (redisTemplate.hasKey(keySuffix)) {
                return ResultInfo.error("充值操作频繁，请稍后再试！");
            }
            if (!lock.lock()) {
                return ResultInfo.error("充值操作频繁，请稍后再试！");
            }
            redisTemplate.opsForValue().setIfAbsent(keySuffix, "1", 10, TimeUnit.SECONDS);
            response.setData(rechargeService.doPayV1(loginUserAPP, req));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doPayV1", req, loginUserAPP);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("失败:{}", e.getMessage());
        } catch (Exception e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("doAgentPay失败,req:{}", JSONObject.toJSONString(req), e);
        } finally {
            lock.unlock();
        }
        logger.info("/v3/doAgentPay耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }



}
