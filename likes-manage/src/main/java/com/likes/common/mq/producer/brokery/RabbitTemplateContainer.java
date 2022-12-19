package com.likes.common.mq.producer.brokery;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.likes.common.exception.MessageRunTimeException;
import com.likes.common.model.mq.Message;
import com.likes.common.model.mq.MessageType;
import com.likes.common.mq.producer.conveter.GenericMessageConverter;
import com.likes.common.mq.producer.conveter.JacksonSerializerFactory;
import com.likes.common.mq.producer.conveter.RabbitMessageConverter;
import com.likes.common.mq.producer.conveter.ser.Serializer;
import com.likes.common.mq.producer.conveter.ser.SerializerFactory;
import com.likes.common.mq.producer.service.IBrokerMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * @description: 池化RabbitTemplate
 * 每一个topic对应一个RabbitTemplate的好处
 * 1、提高发送的效率
 * 2、可以根据不同的需求定制不同的RabbitTemplate，比如每一个topic都有自己的routingKey规则
 */
@Component
public class RabbitTemplateContainer implements RabbitTemplate.ConfirmCallback {

    protected static Logger logger = LoggerFactory.getLogger(RabbitBrokerImpl.class);

    @Autowired
    private IBrokerMessageService iMessageStoreService;

    private final Map<String /* TOPIC */, RabbitTemplate> rabbitTemplateMap = Maps.newConcurrentMap();

    private final Splitter splitter = Splitter.on("#");

    private final SerializerFactory serializerFactory = JacksonSerializerFactory.INSTANCE;

    @Autowired
    private ConnectionFactory connectionFactory;

    public RabbitTemplate getTemplate(Message message) throws MessageRunTimeException {
        Preconditions.checkNotNull(message);
        String topic = message.getTopic();
        RabbitTemplate rabbitTemplate = rabbitTemplateMap.get(topic);
        if (rabbitTemplate != null) {
            return rabbitTemplate;
        }
        logger.info("topic={} is not exists, create", topic);
        RabbitTemplate newTemplate = new RabbitTemplate(connectionFactory);
        newTemplate.setExchange(topic);
        newTemplate.setRoutingKey(message.getRoutingKey());
        newTemplate.setRetryTemplate(new RetryTemplate());

        // 添加序列化反序列化和converter对象
        Serializer serializer = serializerFactory.create();
        GenericMessageConverter gmc = new GenericMessageConverter(serializer);
        RabbitMessageConverter rmc = new RabbitMessageConverter(gmc);
        newTemplate.setMessageConverter(rmc);

        String messageType = message.getMessageType();
        if (!MessageType.RAPID.equals(messageType)) {
            newTemplate.setConfirmCallback(this);
        }
        rabbitTemplateMap.putIfAbsent(topic, newTemplate);
        return rabbitTemplateMap.get(topic);

    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        //消息应答
        List<String> strings = splitter.splitToList(correlationData.getId());
        String messageId = strings.get(0);
        long sendTime = Long.parseLong(strings.get(1));
        String messageType = strings.get(2);
        if (ack) {
            // 当Broker 返回ACK成功时, 就是更新一下日志表里对应的消息发送状态为 SEND_OK
            // 如果当前消息类型为reliant 我们就去数据库查找并进行更新
            if (MessageType.RELIANT.endsWith(messageType)) {
                this.iMessageStoreService.succuess(messageId);
            }
            logger.info("发送消息成功，confirm messageId={}, sendTime={}", messageId, sendTime);
        } else {
            logger.info("发送消息失败，confirm messageId={}, sendTime={}", messageId, sendTime);

        }
    }
}
