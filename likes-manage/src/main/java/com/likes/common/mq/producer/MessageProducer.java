package com.likes.common.mq.producer;

import com.likes.common.exception.MessageRunTimeException;
import com.likes.common.model.mq.Message;

import java.util.List;

public interface MessageProducer {

    void send(Message message) throws MessageRunTimeException;

    void send(List<Message> messages) throws MessageRunTimeException;
}
