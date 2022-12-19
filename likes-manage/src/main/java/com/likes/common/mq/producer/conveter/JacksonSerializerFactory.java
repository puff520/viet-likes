package com.likes.common.mq.producer.conveter;


import com.likes.common.model.mq.Message;
import com.likes.common.mq.producer.conveter.ser.Serializer;
import com.likes.common.mq.producer.conveter.ser.SerializerFactory;

public class JacksonSerializerFactory implements SerializerFactory {

    public static final SerializerFactory INSTANCE = new JacksonSerializerFactory();


    @Override
    public Serializer create() {
        return JacksonSerializer.createParametricType(Message.class);
    }
}
