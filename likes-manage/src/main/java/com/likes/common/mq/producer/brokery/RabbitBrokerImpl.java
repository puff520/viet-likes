package com.likes.common.mq.producer.brokery;


import com.alibaba.fastjson.JSON;
import com.likes.common.enums.BrokerMessageStatus;
import com.likes.common.model.mq.Message;
import com.likes.common.model.mq.MessageType;
import com.likes.common.mq.producer.service.IBrokerMessageService;
import com.likes.common.mybatis.entity.BrokerMessage;
import com.likes.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class RabbitBrokerImpl implements RabbitBroker {

    protected static Logger logger = LoggerFactory.getLogger(RabbitBrokerImpl.class);

    @Autowired
    private RabbitTemplateContainer rabbitTemplateContainer;

    @Autowired
    private IBrokerMessageService iMessageStoreService;

    @Override
    public void rapidSend(Message message) {
        message.setMessageType(MessageType.RAPID);
        sendKernel(message);
    }

    /**
     * 发送消息的核心方法， 使用异步线程池进行发送消息
     *
     * @param message
     */
    private void sendKernel(Message message) {
        AsyncBaseQueue.submit(() -> {
            CorrelationData correlationData =
                    new CorrelationData(
                            String.format("%s#%s#%s",
                                    message.getMessageId(),
                                    System.currentTimeMillis(),
                                    message.getMessageType()));

            String topic = message.getTopic();
            String routingKey = message.getRoutingKey();
            RabbitTemplate rabbitTemplate = rabbitTemplateContainer.getTemplate(message);
            rabbitTemplate.convertAndSend(topic, routingKey, message, correlationData);
            logger.info("#RabbitBrokerImpl.sendKernel# 发送消息到RabbitMQ,messageId={}", message.getMessageId());
        });

    }

    @Override
    public void confirmSend(Message message) {
        message.setMessageType(MessageType.CONFIRM);
        sendKernel(message);
    }

    @Override
    public void reliantSend(Message message) {
        message.setMessageType(MessageType.RELIANT);
        BrokerMessage bm = iMessageStoreService.selectByMessageId(message.getMessageId());
        if (bm == null) {
            //1. 把数据库的消息发送日志先记录好
            Date now = new Date();
            BrokerMessage brokerMessage = new BrokerMessage();
            brokerMessage.setMessageId(message.getMessageId());
            brokerMessage.setStatus(BrokerMessageStatus.SENDING.getCode());
            //tryCount 在最开始发送的时候不需要进行设置
            brokerMessage.setNextRetry(DateUtils.addMinutes(now, 1));
            brokerMessage.setCreateTime(now);
            brokerMessage.setUpdateTime(now);
            brokerMessage.setMessage(JSON.toJSONString(message));
            iMessageStoreService.saveMessage(brokerMessage);
        }
        //2. 执行真正的发送消息逻辑
        sendKernel(message);
    }

    @Override
    public void sendMessages() {
        List<Message> messages = MessageHolder.clear();
        messages.forEach(message -> {
            MessageHolderAsyncQueue.submit(() -> {
                CorrelationData correlationData =
                        new CorrelationData(
                                String.format("%s#%s#%s",
                                        message.getMessageId(),
                                        System.currentTimeMillis(),
                                        message.getMessageType()));

                String topic = message.getTopic();
                String routingKey = message.getRoutingKey();
                RabbitTemplate rabbitTemplate = rabbitTemplateContainer.getTemplate(message);
                rabbitTemplate.convertAndSend(topic, routingKey, message, correlationData);
                logger.info("#RabbitBrokerImpl.sendKernel# 发送消息到RabbitMQ,messageId={}", message.getMessageId());
            });
        });
    }
}
