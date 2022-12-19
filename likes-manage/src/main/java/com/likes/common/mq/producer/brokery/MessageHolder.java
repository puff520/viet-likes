package com.likes.common.mq.producer.brokery;

import com.google.common.collect.Lists;
import com.likes.common.model.mq.Message;

import java.util.List;

public class MessageHolder {

    private final List<Message> messages = Lists.newArrayList();

    public static final ThreadLocal<MessageHolder> holder = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return new MessageHolder();
        }

    };

    public static void add(Message message) {
        holder.get().messages.add(message);
    }

    public static List<Message> clear() {
        List<Message> temp = Lists.newArrayList(holder.get().messages);
        holder.remove();
        return temp;
    }
}
