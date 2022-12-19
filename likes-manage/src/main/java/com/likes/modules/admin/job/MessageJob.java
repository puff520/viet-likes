package com.likes.modules.admin.job;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.enums.BrokerMessageStatus;
import com.likes.common.model.mq.Message;
import com.likes.common.mq.producer.brokery.RabbitBroker;
import com.likes.common.mq.producer.service.IBrokerMessageService;
import com.likes.common.mybatis.entity.BrokerMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageJob {

    private static final Integer max_retry_count = 3;

    Logger log = LoggerFactory.getLogger(MessageJob.class);

    @Autowired
    private IBrokerMessageService iMessageStoreService;

    @Autowired
    private RabbitBroker rabbitBroker;

//    @Scheduled(fixedDelay = 60000*2)
    private void messageJob() {
        List<BrokerMessage> datList = iMessageStoreService.queryTimeoutMessage4Retry(BrokerMessageStatus.SENDING);
        datList.forEach(brokerMessage -> {
            if (brokerMessage.getTryCount() >= max_retry_count) {
                this.iMessageStoreService.failure(brokerMessage.getMessageId());
                log.warn("消失设置为失败，消息id: {}  ====== ", brokerMessage.getMessageId());
            } else {
                iMessageStoreService.updateTryCount(brokerMessage.getMessageId());
                Message message = JSONObject.parseObject(brokerMessage.getMessage(), Message.class);
                rabbitBroker.reliantSend(message);
            }
        });
    }



}
