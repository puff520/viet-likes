package com.likes.modules.admin.report.task;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.likes.modules.admin.report.service.ExpFundStatementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author xiaoming
 * @ClassName
 * @Description
 * @Date 8:43 下午 7/29/20
 **/
@Component
public class ReportScheduledTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ExpFundStatementService expFundStatementService;

    /**
     * @return
     * @Author xiaoming
     * @Description 资金报表定时任务 每天凌晨3点开始执行
     * @Date 4:06 下午 7/29/20
     * @Param
     **/
    @Scheduled(cron = "0 0 3 * * ?")
    public void statisticalDailyFundStatement() {
        long start = System.currentTimeMillis();
        logger.info("统计资金报表定时任务 开始：{}", DateUtil.yesterday());
        DateTime dateTime = DateUtil.yesterday();
        String startTime = DateUtil.format(dateTime, DatePattern.PURE_DATE_PATTERN);
        String endTime = DateUtil.format(dateTime, DatePattern.PURE_DATE_PATTERN);
        expFundStatementService.manualStatisticsFunds(startTime, endTime, null);
        logger.info("统计资金报表定时任务结束：{}", DateUtil.yesterday());
    }
}
