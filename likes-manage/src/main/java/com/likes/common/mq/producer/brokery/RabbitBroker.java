package com.likes.common.mq.producer.brokery;


import com.likes.common.model.mq.Message;

public interface RabbitBroker {

    void rapidSend(Message message);

    void confirmSend(Message message);

    void reliantSend(Message message);

    void sendMessages();
}
