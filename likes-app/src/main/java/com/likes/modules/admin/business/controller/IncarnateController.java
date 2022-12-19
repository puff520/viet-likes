package com.likes.modules.admin.business.controller;

import com.likes.common.BaseController;
import com.likes.common.enums.LoginUserTypeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.pay.MemBankAccountTypeEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.IncarnateRequest;
import com.likes.common.mybatis.entity.MemBankaccount;
import com.likes.common.util.LogUtils;
import com.likes.common.util.StringUtils;
import com.likes.common.util.redis.RedisLock;
import com.likes.modules.admin.business.service.IncarnateService;
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
 * 提现
 *
 * @author bjkj
 */

@Controller
@RequestMapping(value = "/incarnate")
public class IncarnateController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private IncarnateService incarnateService;
    @Resource
    private RedisTemplate redisTemplate;


    @ResponseBody
    @RequestMapping(name = "设置会员提现账户", value = "/doSetAnchorBank", method = RequestMethod.POST)
    public ResultInfo doSetAnchorBank(MemBankaccount memBankaccount) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(incarnateService.doSetAnchorBank(loginUserAPP, memBankaccount));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error(getClass().getName() + ".doSetAnchorBank 失败:{}", e.getMessage());
        } catch (Exception e) {
            response.setResultInfo("100000001", null);
            logger.error(getClass().getName() + ".doSetAnchorBank doSetAnchorBank error", e);
        }
        logger.info("/doSetAnchorBank耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "修改会员提现账户", value = "/resetAnchorBank", method = RequestMethod.POST)
    public ResultInfo resetAnchorBank(MemBankaccount memBankaccount) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(incarnateService.reSetAnchorBank(loginUserAPP, memBankaccount));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error(getClass().getName() + ".resetAnchorBank 失败:{}", e.getMessage());
        } catch (Exception e) {
            response.setResultInfo("100000001", null);
            logger.error(getClass().getName() + ".resetAnchorBank doSetAnchorBank error", e);
        }
        logger.info("/resetAnchorBank{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "获取用户提现数据", value = "/v2/getAnchorIncarnate", method = RequestMethod.GET)
    public ResultInfo anchorIncarnateDataV2() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(incarnateService.anchorIncarnateDataV2(loginUserAPP));
            LogUtils.logUserModifyOperates(getClass().getName() + ".anchorIncarnateDataV2", response, loginUserAPP);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("失败:{}", e.getMessage());
        } catch (Exception e) {
            response.setResultInfo(StatusCode.OPERATION_FAILED.getCode(), e.getMessage());
            logger.error("anchorIncarnate error", e);
        }
        logger.info("/v2/anchorIncarnate耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "用户申请提现", value = "/v2/doIncarnate", method = RequestMethod.POST)
    public ResultInfo doIncarnateV2(IncarnateRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        RedisLock lock = new RedisLock(RedisLock.FINANCE_APP_WITHDRAWAL_APPLY_LOCK, 0, 100 * 1000);
        try {
            if (StringUtils.isEmpty(req.getPaypassword()) || null == req.getApycamt()) {
                return ResultInfo.paramsError();
            }
            LoginUser loginUserAPP = getLoginUserAPP();
            // 控制频率
            String keySuffix = RedisLock.FINANCE_APP_WITHDRAWAL_APPLY + loginUserAPP.getMemid();
            if (redisTemplate.hasKey(keySuffix)) {
                new BusinessException(StatusCode.LIVE_ERROR_501.getCode(), "提现操作频繁，请稍后再试！");
            }
            boolean haveAuth = redisTemplate.opsForValue().setIfAbsent(keySuffix, "1", 10, TimeUnit.SECONDS);
            if (!haveAuth) {
                new BusinessException(StatusCode.LIVE_ERROR_501.getCode(), "提现操作频繁，请稍后再试！");
            }
            if (!lock.lock()) {
                new BusinessException(StatusCode.LIVE_ERROR_501.getCode(), "提现操作频繁，请稍后再试！");
            }
            response.setData(incarnateService.doIncarnateV2(loginUserAPP, req));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doIncarnateV2", req, loginUserAPP);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("失败:{}", e.getMessage());
        } catch (Exception e) {
            response.setResultInfo("100000001", null);
            logger.error("doIncarnateV2 error", e);
        } finally {
            lock.unlock();
        }
        logger.info("/doIncarnateV2耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

}
