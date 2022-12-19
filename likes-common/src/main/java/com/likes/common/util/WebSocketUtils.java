//package com.likes.common.util;
//
//import com.likes.common.model.common.ChatBody;
//import com.likes.common.spring.SpringContextUtils;
//import org.springframework.data.redis.core.StringRedisTemplate;
//
//public class WebSocketUtils {
//
//
//    /**
//     * 推送消息到webscoket
//     * @param stream room的steamKey
//     * @param chatBody 消息
//     */
//    public static void sendMsg(String stream, ChatBody chatBody){
//        SpringContextUtils.getBean(StringRedisTemplate.class).convertAndSend(stream,JsonUtil.toJson(chatBody));
//    }
//
//}
