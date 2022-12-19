package com.likes.common.mq.producer.service.impl;

import com.likes.common.enums.BrokerMessageStatus;
import com.likes.common.mq.producer.service.IBrokerMessageService;
import com.likes.common.mybatis.entity.BrokerMessage;
import com.likes.common.mybatis.mapper.BrokerMessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 消息记录表 服务实现类
 * </p>
 *
 * @author xxx
 * @since 2022-01-03
 */
@Service
public class BrokerMessageServiceImpl implements IBrokerMessageService {

    @Resource
    private BrokerMessageMapper brokerMessageMapper;

    @Override
    public boolean saveMessage(BrokerMessage message) {
        int row = this.brokerMessageMapper.insertSelective(message);
        return row > 0;
    }

    @Override
    public void succuess(String messageId) {
        BrokerMessage brokeryMessage = new BrokerMessage();
        brokeryMessage.setMessageId(messageId);
        brokeryMessage.setStatus(BrokerMessageStatus.SEND_OK.getCode());
        this.brokerMessageMapper.updateByPrimaryKeySelective(brokeryMessage);
    }

    @Override
    public void failure(String messageId) {
        BrokerMessage brokeryMessage = new BrokerMessage();
        brokeryMessage.setMessageId(messageId);
        brokeryMessage.setStatus(BrokerMessageStatus.SEND_FAIL.getCode());
        this.brokerMessageMapper.updateByPrimaryKeySelective(brokeryMessage);
    }

    public List<BrokerMessage> queryTimeoutMessage4Retry(BrokerMessageStatus brokerMessageStatus) {
        List<BrokerMessage> list = brokerMessageMapper.queryBrokeryMessageStatus4Timeout(brokerMessageStatus.getCode());
        return list;
    }

    @Override
    public BrokerMessage selectByMessageId(String messageId) {
        return this.brokerMessageMapper.selectByPrimaryKey(messageId);
    }

    @Override
    public void updateTryCount(String messageId) {
        brokerMessageMapper.updateForTryCount(messageId, new Date());
    }
}
