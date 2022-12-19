package com.likes.modules.admin.finance.task;

import com.likes.common.util.DateUtils;
import com.likes.modules.admin.finance.service.RechargeOrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

import javax.annotation.Resource;

/**
 * @author 阿布 代理结算定时任务
 */
@Component
public class PayTask {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RechargeOrderService rechargeOrderService;

    @Scheduled(cron = "59 58 23 * * ?")
    public void scheduled() {
        String type = "(线上订单过期定时任务执行)";
        try {
            logger.info("线上订单过期定时任务开始：{}", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
            logger.info("currentThread:{}", Thread.currentThread().getName());
            this.rechargeOrderService.doDayExportOrder();
            logger.info("线上订单过期定时任务结束：{}", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            logger.error("线上订单过期定时任务,doDayExportNew,出错,date:{},type:{}", type, e);
        }
    }
}