package com.likes.common.mq.producer.conveter.ser;

public interface Serializer {


    byte[] serializeRaw(Object data);

    String serialize(Object data);

    byte[] deserializeRaw(Object data);

    <T> T deserialize(String content);

    <T> T deserialize(byte[] content);
}
