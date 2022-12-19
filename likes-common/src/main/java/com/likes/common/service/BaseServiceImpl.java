package com.likes.common.service;

import com.likes.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author abu
 */
@Service
public class BaseServiceImpl implements BaseService {
    @Resource
    public HttpServletRequest request;
    @Value("${userSessionKey}")
    protected String userSessionKey;
//    @Resource
//    private ChatRest chatRest;
//
//    @Override
//    public void sendRoomMsg(ChatBody message) throws BusinessException {
//        //ChatServer.sendRoomMsg(message);
//        chatRest.sendRoomMsg(message);
//    }
//
//    @Override
//    public void sendAllRoomMsg(ChatBody message) throws BusinessException {
//        /*List<String> streams = basAnchorRoomService.getOnlinStreams();
//        if (streams != null && streams.size() > 0) {
//            ChatServer.sendAllRoomMsg(streams, message);
//        }*/
//        chatRest.sendAllRoomMsg(message);
//    }

}
