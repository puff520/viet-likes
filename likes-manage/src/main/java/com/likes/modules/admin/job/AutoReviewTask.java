package com.likes.modules.admin.job;

import cn.hutool.core.util.IdUtil;
import com.likes.common.constant.RabbitConstants;
import com.likes.common.model.mq.Message;
import com.likes.common.model.mq.MessageType;
import com.likes.common.mq.producer.MessageProducer;
import com.likes.common.mybatis.entity.TaskOrder;
import com.likes.common.mybatis.mapper.TaskOrderMapper;
import com.likes.common.util.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AutoReviewTask {

    @Resource
    private TaskOrderMapper taskOrderMapper;
    @Resource
    MessageProducer messageProducer;


//    @Scheduled(cron = "0 */1 * * * ?")
    @Transactional
    public void autoReviewTask() {
        List<Long> idlist = taskOrderMapper.selectWaitReviceIdList();
        idlist.forEach(orderId -> {
            System.out.println(1111111);
            TaskOrder taskOrder = new TaskOrder();
            taskOrder.setId(orderId);
            taskOrder.setSendStatus(1);
            taskOrderMapper.updateByPrimaryKeySelective(taskOrder);
            sendTaskMessage(orderId);
        });
    }


    private void sendTaskMessage(Long taskId) {
        Map<String,Object> attributes = new HashMap<>(1);
        attributes.put("taskId",taskId);
        Message message = MessageBuilder.create().
                withMessageId(IdUtil.fastSimpleUUID())
                .withRoutingKey(RabbitConstants.Key.TASK_KEY)
                .withTopic(RabbitConstants.TASK_TOPIC)
                .withAttributes(attributes)
                .withMessageType(MessageType.RELIANT)
                .build();
        messageProducer.send(message);
    }


}
