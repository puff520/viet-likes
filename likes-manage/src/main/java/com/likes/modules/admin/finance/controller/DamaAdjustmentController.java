package com.likes.modules.admin.finance.controller;


import com.likes.common.BaseController;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.FinanceDamaAdjustmentRequest;
import com.likes.common.mybatis.entity.FinanceDamaAdjustment;
import com.likes.common.util.LogUtils;
import com.likes.common.util.redis.RedisLock;
import com.likes.modules.admin.finance.service.impl.BalanceAdjustmentService;
import com.likes.modules.admin.finance.service.impl.DamaAdjustmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/damaAdjustment")
public class DamaAdjustmentController extends BaseController {

    private final Logger logger = LogManager.getLogger(getClass());

    @Resource
    private DamaAdjustmentService damaAdjustmentService;
    @Resource
    private BalanceAdjustmentService balanceAdjustmentService;
    @Resource
    private RedissonClient redissonClient;

    @GetMapping("/list")
    public ResultInfo list(String mobileno,String nickname, String uniqueId, Integer pageNo, Integer pageSize) {
        try {
            PageBounds page = new PageBounds();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            return balanceAdjustmentService.list(mobileno,nickname, uniqueId, page);
        } catch (BusinessException e) {
            logger.error("{}/list,出错信息:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error(e.getMessage());
        } catch (Exception e) {
            logger.error("{}/list,出错信息:{}", getClass().getName(), e.getMessage(), e);
            return ResultInfo.error();
        }
    }

    @PostMapping("/adjustment")
    public ResultInfo adjustment(@RequestBody FinanceDamaAdjustment financeDamaAdjustment) {
        RLock lock = null;
        try {
            lock = redissonClient.getReadWriteLock(RedisLock.FINANCE_MANAGE_DAMALIANG_ADJUSTMENT + financeDamaAdjustment.getAccno()).writeLock();
            boolean bool = lock.tryLock(0, 60, TimeUnit.SECONDS);
            if (!bool) {
                throw new BusinessException("操作太频繁，请稍后再试！");
            }
            financeDamaAdjustment.setCreateUser(getLoginAdmin().getAccno());
            financeDamaAdjustment.setUpdateUser(getLoginAdmin().getAccno());
            damaAdjustmentService.adjustment(financeDamaAdjustment);
            LogUtils.logUserModifyOperates(getClass().getName() + ".batchAdjustment", financeDamaAdjustment, getLoginAdmin());
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

    @PostMapping("/batch/adjustment")
    public ResultInfo batchAdjustment(@RequestBody FinanceDamaAdjustmentRequest financeDamaAdjustmentRequest) {
        RLock lock = null;
        try {
            lock = redissonClient.getReadWriteLock(RedisLock.FINANCE_MANAGE_BATCH_DAMALIANG_ADJUSTMENT + getLoginAdmin().getMemid()).writeLock();
            boolean bool = lock.tryLock(0, 60, TimeUnit.SECONDS);
            if (!bool) {
                throw new BusinessException("操作太频繁，请稍后再试！");
            }
            financeDamaAdjustmentRequest.setCreateUser(getLoginAdmin().getAccno());
            financeDamaAdjustmentRequest.setUpdateUser(getLoginAdmin().getAccno());
            damaAdjustmentService.batchAdjustment(financeDamaAdjustmentRequest);
            LogUtils.logUserModifyOperates(getClass().getName() + ".batchAdjustment", financeDamaAdjustmentRequest, getLoginAdmin());
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
