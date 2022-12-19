package com.likes.modules.admin.finance.controller;


import cn.hutool.core.util.ObjectUtil;
import com.likes.common.BaseController;
import com.likes.common.annotation.Syslog;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.FinanceBalanceAdjustmentRequest;
import com.likes.common.mybatis.entity.FinanceBalanceAdjustment;
import com.likes.common.mybatis.entity.MemLogin;
import com.likes.common.service.member.MemLoginService;
import com.likes.common.util.LogUtils;
import com.likes.common.util.redis.RedisLock;
import com.likes.modules.admin.finance.service.impl.BalanceAdjustmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/balanceAdjustment")
public class BalanceAdjustmentController extends BaseController {

    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private BalanceAdjustmentService financeMemService;
    @Autowired
    private MemLoginService memLoginService;
    @Resource
    private RedissonClient redissonClient;

    @GetMapping("/list")
    public ResultInfo list(String email, String nickname, String uniqueId, Integer pageNo, Integer pageSize) {
        try {
            PageBounds page = new PageBounds();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            return financeMemService.list(email, nickname, uniqueId, page);
        } catch (BusinessException e) {
            logger.error("{}/list,出错信息:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error(e.getMessage());
        } catch (Exception e) {
            logger.error("{}/list,出错信息:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error();
        }
    }



    //加款扣款
    @Syslog("加款扣款")
    @PostMapping("/adjustment")
    public ResultInfo adjustment(@RequestBody FinanceBalanceAdjustment financeBalanceAdjustment) {
        RLock lock = null;
        try {
            lock = redissonClient.getReadWriteLock(RedisLock.FINANCE_MANAGE_BALANCE_ADJUSTMENT + financeBalanceAdjustment.getAccno()).writeLock();
            boolean bool = lock.tryLock(0, 60, TimeUnit.SECONDS);
            if (!bool) {
                throw new BusinessException("操作太频繁，请稍后再试！");
            }
            financeBalanceAdjustment.setCreateUser(getLoginAdmin().getAccno());
            financeBalanceAdjustment.setUpdateUser(getLoginAdmin().getAccno());
            financeMemService.adjustment(financeBalanceAdjustment);
            LogUtils.logUserModifyOperates(getClass().getName() + ".batchAdjustment", financeBalanceAdjustment, getLoginAdmin());
            return ResultInfo.ok();
        } catch (BusinessException e) {

            logger.error("{}/adjustment,出错信息:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error(e.getMessage());
        } catch (Exception e) {
            logger.error("{}/adjustment,出错信息:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error();
        } finally {
            if (lock != null) {
                lock.unlock();
            }
        }
    }

    @Syslog("增加彩金")
    @PostMapping("/adJackpot")
    public ResultInfo adJackpot(@RequestBody FinanceBalanceAdjustment financeBalanceAdjustment) {
        RLock lock = null;
        try {
            lock = redissonClient.getReadWriteLock(RedisLock.FINANCE_MANAGE_BALANCE_ADJACKPOT_ + financeBalanceAdjustment.getAccno()).writeLock();
            boolean bool = lock.tryLock(0, 60, TimeUnit.SECONDS);
            if (!bool) {
                throw new BusinessException("操作太频繁，请稍后再试！");
            }
            financeBalanceAdjustment.setCreateUser(getLoginAdmin().getAccno());
            financeBalanceAdjustment.setUpdateUser(getLoginAdmin().getAccno());
            financeBalanceAdjustment.setType(70);
            financeMemService.adJackpot(financeBalanceAdjustment);
            LogUtils.logUserModifyOperates(getClass().getName() + ".adJackpot", financeBalanceAdjustment, getLoginAdmin());
            return ResultInfo.ok();
        } catch (BusinessException e) {
            logger.error("{}/adJackpot,出错信息:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error(e.getMessage());
        } catch (Exception e) {
            logger.error("{}/adJackpot,出错信息:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error();
        } finally {
            if (lock != null) {
                lock.unlock();
            }
        }
    }


    //批量
    @Syslog("批量")
    @PostMapping("/batch/adjustment")
    public ResultInfo batchAdjustment(@RequestBody FinanceBalanceAdjustmentRequest financeBalanceAdjustmentRequest) {
        RLock lock = null;
        try {
            lock = redissonClient.getReadWriteLock(RedisLock.FINANCE_MANAGE_BATCH_BALANCE_ADJUSTMENT + getLoginAdmin().getMemid()).writeLock();
            boolean bool = lock.tryLock(0, 60, TimeUnit.SECONDS);
            if (!bool) {
                throw new BusinessException("操作太频繁，请稍后再试！");
            }
            financeBalanceAdjustmentRequest.setCreateUser(getLoginAdmin().getAccno());
            financeBalanceAdjustmentRequest.setUpdateUser(getLoginAdmin().getAccno());
            financeMemService.batchAdjustment(financeBalanceAdjustmentRequest);
            LogUtils.logUserModifyOperates(getClass().getName() + ".batchAdjustment", financeBalanceAdjustmentRequest, getLoginAdmin());
            return ResultInfo.ok();
        } catch (BusinessException e) {
            logger.error("{}/adjustment,出错信息:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error(e.getMessage());
        } catch (Exception e) {
            logger.error("{}/adjustment,出错信息:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error();
        } finally {
            lock.unlock();
        }
    }

}
