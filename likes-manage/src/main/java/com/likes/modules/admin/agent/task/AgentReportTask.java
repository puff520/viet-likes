package com.likes.modules.admin.agent.task;

import com.likes.common.util.DateUtils;
import com.likes.modules.admin.agent.service.AgentReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 阿布 代理结算定时任务
 */
@Component
public class AgentReportTask {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AgentReportService agentReportService;

//    @Scheduled(cron = "00 00 09 * * ?")
//    @Scheduled(cron = "5 48 10 * * ? ")
    // @Scheduled(initialDelay = 1000 * 60 * 1, fixedRate = 1000 * 60 * 30) // 启动时执行一次，之后每隔30分钟执行一次
    public void scheduled() {
        String day = DateUtils.dayBeforeStratStr();
        String type = "(定时任务执行)";
        try {
            logger.info("代理结算定时任务开始：{}", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
            logger.info("currentThread:{}", Thread.currentThread().getName());
            this.agentReportService.doDayExportNew(day, type);
            logger.info("代理结算定时任务结束：{}", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            logger.error("代理结算定时任务,doDayExportNew,出错,date:{},type:{}", day, type, e);
        }
    }
}
