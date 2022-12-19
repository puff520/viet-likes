package com.likes.modules.admin.job;

import cn.hutool.core.util.IdUtil;
import com.likes.common.constant.RabbitConstants;
import com.likes.common.model.mq.Message;
import com.likes.common.model.mq.MessageType;
import com.likes.common.mq.producer.MessageProducer;
import com.likes.common.mybatis.mapper.MemYuebaoRecordMapper;
import com.likes.common.util.DateUtils;
import com.likes.common.util.MessageBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Log4j2
public class AssetEarnTask {

    @Resource
    private MemYuebaoRecordMapper memYuebaoRecordMapper;

    private final String beginTime = DateUtils.dayBeginStr();

    private final String endTime = DateUtils.dayEndStr();

    @Resource
    MessageProducer messageProducer;

    @Scheduled(cron = "0 5 0 * * ? ")
    public void dailyTask1() {
        try {
            List<String> accNoList = memYuebaoRecordMapper.yueBaoAccList(beginTime, endTime, 1L);
            for (String accno : accNoList) {
                sendTaskMessage(accno, RabbitConstants.Key.DAILY_KEY);
            }
        } catch (Exception e) {
            log.error("执行定时任务错误 ：============》》 {}", e);
        }

    }

    @Scheduled(cron = "0 10 0 * * ? ")
    public void dailyTask2() {
        try {
            List<String> accNoList = memYuebaoRecordMapper.yueBaoAccList(beginTime, endTime, 2L);
            for (String accno : accNoList) {
                sendTaskMessage(accno, RabbitConstants.Key.SEVEN_DAY_KEY);
            }
        } catch (Exception e) {
            log.error("执行定时任务错误 ：============》》 {}", e);
        }

    }

    @Scheduled(cron = "0 15 0 * * ? ")
    public void dailyTask3() {
        try {
            List<String> accNoList = memYuebaoRecordMapper.yueBaoAccList(beginTime, endTime, 3L);
            for (String accno : accNoList) {
                sendTaskMessage(accno, RabbitConstants.Key.TWO_WEEK_KEY);
            }
        } catch (Exception e) {
            log.error("执行定时任务错误 ：============》》 {}", e);
        }

    }

    @Scheduled(cron = "0 20 0 * * ? ")
    public void dailyTask4() {
        try {
            List<String> accNoList = memYuebaoRecordMapper.yueBaoAccList(beginTime, endTime, 4L);
            for (String accno : accNoList) {
                sendTaskMessage(accno, RabbitConstants.Key.MONTH_KEY);
            }
        } catch (Exception e) {
            log.error("执行定时任务错误 ：============》》 {}", e);
        }

    }



    private void sendTaskMessage(String accno,String routingKey) {
        Map<String, Object> attributes = new HashMap<>(1);
        attributes.put("accno", accno);
        Message message = MessageBuilder.create().
                withMessageId(IdUtil.fastSimpleUUID())
                .withRoutingKey(routingKey)
                .withTopic(RabbitConstants.YUEBAO_TOPIC)
                .withAttributes(attributes)
                .withMessageType(MessageType.RELIANT)
                .build();
        messageProducer.send(message);
    }

}
