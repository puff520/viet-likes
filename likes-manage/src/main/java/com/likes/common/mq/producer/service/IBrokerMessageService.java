package com.likes.common.mq.producer.service;

import com.likes.common.enums.BrokerMessageStatus;
import com.likes.common.mybatis.entity.BrokerMessage;

import java.util.List;

/**
 * <p>
 * 消息记录表 服务类
 * </p>
 *
 * @author xxx
 * @since 2022-01-03
 */
public interface IBrokerMessageService {


    boolean saveMessage(BrokerMessage message);

    void succuess(String messageId);

    void failure(String messageId);

    List<BrokerMessage> queryTimeoutMessage4Retry(BrokerMessageStatus brokerMessageStatus);

    BrokerMessage selectByMessageId(String messageId);

    void updateTryCount(String messageId);
}
