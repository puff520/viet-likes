package com.likes.common.mq.producer.conveter;

import com.google.common.base.Preconditions;
import com.google.common.base.Preconditions;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;


public class RabbitMessageConverter implements MessageConverter {


    private final GenericMessageConverter delegate;

    //  private final String delaultExprie = String.valueOf(24 * 60 * 60 * 1000);
    public RabbitMessageConverter(GenericMessageConverter genericMessageConverter) {
        Preconditions.checkNotNull(genericMessageConverter);
        this.delegate = genericMessageConverter;
    }


    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        //      messageProperties.setExpiration(delaultExprie);
        com.likes.common.model.mq.Message message = (com.likes.common.model.mq.Message ) object;
        messageProperties.setDelay(message.getDelayMills());
        messageProperties.setMessageId(message.getMessageId());
        return this.delegate.toMessage(object, messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        com.likes.common.model.mq.Message  msg = (com.likes.common.model.mq.Message) this.delegate.fromMessage(message);
        return msg;
    }
}


