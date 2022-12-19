package com.likes.common.mq.receiver;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.likes.common.constant.RabbitConstants;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.AssetTypeEnum;
import com.likes.common.enums.YueaboOperateEnum;
import com.likes.common.model.AssetChangeBO;
import com.likes.common.mybatis.entity.YuebaoDividendFail;
import com.likes.common.mybatis.mapper.MemYuebaoRecordMapper;
import com.likes.common.mybatis.mapper.YuebaoDividendFailMapper;
import com.likes.common.service.yuebao.YuebaoChangeService;
import com.likes.common.util.CommonUtils;
import com.likes.common.util.DateUtils;
import com.likes.common.util.redis.RedisBaseUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.ExecutorService;

@Component
@Log4j2
@Transactional
public class YuebaoConsumer {


    private static final ExecutorService taskCachedThreadPool = CommonUtils.getMaxThreadPoolExecutor();
    @Resource
    private MemYuebaoRecordMapper memYuebaoRecordMapper;
    @Resource
    private YuebaoChangeService yuebaoChangeService;

    @Resource
    YuebaoDividendFailMapper yuebaoDividendFailMapper;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = RabbitConstants.Queue.DAILY_QUEUE, durable = "true", autoDelete = "false"), exchange = @Exchange(value = RabbitConstants.YUEBAO_TOPIC), key = {RabbitConstants.Key.DAILY_KEY}))
    @Transactional
    public void asset1(Message message, Channel channel) {
        String accno = null;
        try {
            String messageId = message.getMessageProperties().getMessageId();
            String payload = new String(message.getBody());
            String cacheMessageId = RedisBaseUtil.get(RedisKeys.SYS_RABBITMQ_MSG_ID_KEY + messageId);
            if (messageId.equals(cacheMessageId)) {
                log.warn("该消息已经被消费  ====== {}", message);
                return;

            }
            com.likes.common.model.mq.Message myMsg = JSONUtil.toBean(payload, com.likes.common.model.mq.Message.class);
            accno = myMsg.getAttributes().get("accno").toString();
            autoDividend(accno, AssetTypeEnum.DAILY);
            Long deliveryTag = message.getMessageProperties().getDeliveryTag();
            //手工ACK
            channel.basicAck(deliveryTag, false);
            RedisBaseUtil.set(RedisKeys.SYS_RABBITMQ_MSG_ID_KEY + messageId, messageId, 60 * 60L);
        } catch (Exception e) {
            log.error("执行自动审核队列错误 ：============》》 {}", e);
            String finalAccno = accno;
            taskCachedThreadPool.execute(() -> saveYuebaoErrorRecord(finalAccno, e.getMessage(), AssetTypeEnum.DAILY));
            throw new RuntimeException("用户修改余额出错,队列事务回滚", e.getCause());
        }
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = RabbitConstants.Queue.SEVEN_DAY_QUEUE, durable = "true", autoDelete = "false"), exchange = @Exchange(value = RabbitConstants.YUEBAO_TOPIC), key = {RabbitConstants.Key.SEVEN_DAY_KEY}))
    @Transactional
    public void asset2(Message message, Channel channel) {
        String accno = null;
        try {
            String messageId = message.getMessageProperties().getMessageId();
            String payload = new String(message.getBody());
            String cacheMessageId = RedisBaseUtil.get(RedisKeys.SYS_RABBITMQ_MSG_ID_KEY + messageId);
            if (messageId.equals(cacheMessageId)) {
                log.warn("该消息已经被消费  ====== {}", message);
                return;

            }
            com.likes.common.model.mq.Message myMsg = JSONUtil.toBean(payload, com.likes.common.model.mq.Message.class);
            accno = myMsg.getAttributes().get("accno").toString();
            autoDividend(accno, AssetTypeEnum.SEVEN_DAY);
            Long deliveryTag = message.getMessageProperties().getDeliveryTag();
            //手工ACK
            channel.basicAck(deliveryTag, false);
            RedisBaseUtil.set(RedisKeys.SYS_RABBITMQ_MSG_ID_KEY + messageId, messageId, 60 * 60L);
        } catch (Exception e) {
            log.error("执行自动审核队列错误 ：============》》 {}", e);
            String finalAccno = accno;
            taskCachedThreadPool.execute(() -> saveYuebaoErrorRecord(finalAccno, e.getMessage(), AssetTypeEnum.SEVEN_DAY));
            throw new RuntimeException("用户修改余额出错,队列事务回滚", e.getCause());
        }
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = RabbitConstants.Queue.TWO_WEEK_QUEUE, durable = "true", autoDelete = "false"), exchange = @Exchange(value = RabbitConstants.YUEBAO_TOPIC), key = {RabbitConstants.Key.TWO_WEEK_KEY}))
    @Transactional
    public void asset3(Message message, Channel channel) {
        String accno = null;
        try {
            String messageId = message.getMessageProperties().getMessageId();
            String payload = new String(message.getBody());
            String cacheMessageId = RedisBaseUtil.get(RedisKeys.SYS_RABBITMQ_MSG_ID_KEY + messageId);
            if (messageId.equals(cacheMessageId)) {
                log.warn("该消息已经被消费  ====== {}", message);
                return;

            }
            com.likes.common.model.mq.Message myMsg = JSONUtil.toBean(payload, com.likes.common.model.mq.Message.class);
            accno = myMsg.getAttributes().get("accno").toString();
            autoDividend(accno, AssetTypeEnum.TWO_WEEK);
            Long deliveryTag = message.getMessageProperties().getDeliveryTag();
            //手工ACK
            channel.basicAck(deliveryTag, false);
            RedisBaseUtil.set(RedisKeys.SYS_RABBITMQ_MSG_ID_KEY + messageId, messageId, 60 * 60L);
        } catch (Exception e) {
            log.error("执行自动审核队列错误 ：============》》 {}", e);
            String finalAccno = accno;
            taskCachedThreadPool.execute(() -> saveYuebaoErrorRecord(finalAccno, e.getMessage(), AssetTypeEnum.TWO_WEEK));
            throw new RuntimeException("用户修改余额出错,队列事务回滚", e.getCause());
        }
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = RabbitConstants.Queue.MONTH_QUEUE, durable = "true", autoDelete = "false"), exchange = @Exchange(value = RabbitConstants.YUEBAO_TOPIC), key = {RabbitConstants.Key.MONTH_KEY}))
    @Transactional
    public void asset4(Message message, Channel channel) {
        String accno = null;
        try {
            String messageId = message.getMessageProperties().getMessageId();
            String payload = new String(message.getBody());
            String cacheMessageId = RedisBaseUtil.get(RedisKeys.SYS_RABBITMQ_MSG_ID_KEY + messageId);
            if (messageId.equals(cacheMessageId)) {
                log.warn("该消息已经被消费  ====== {}", message);
                return;

            }
            com.likes.common.model.mq.Message myMsg = JSONUtil.toBean(payload, com.likes.common.model.mq.Message.class);
            accno = myMsg.getAttributes().get("accno").toString();
            autoDividend(accno, AssetTypeEnum.MONTH);
            Long deliveryTag = message.getMessageProperties().getDeliveryTag();
            //手工ACK
            channel.basicAck(deliveryTag, false);
            RedisBaseUtil.set(RedisKeys.SYS_RABBITMQ_MSG_ID_KEY + messageId, messageId, 60 * 60L);
        } catch (Exception e) {
            log.error("执行自动审核队列错误 ：============》》 {}", e);
            String finalAccno = accno;
            taskCachedThreadPool.execute(() -> saveYuebaoErrorRecord(finalAccno, e.getMessage(), AssetTypeEnum.MONTH));
            throw new RuntimeException("用户修改余额出错,队列事务回滚", e.getCause());
        }
    }

    public void autoDividend(String accno, AssetTypeEnum assetTypeEnum) {
        String endTime = DateUtils.timeBeforeEndStr();
        Integer dividendCount = memYuebaoRecordMapper.countYuebaoDividend(DateUtils.dayBeginStr(), DateUtils.dayEndStr(), accno, assetTypeEnum.getValue());
        if (dividendCount > 0) {
            log.error("余额宝派息此用户今天已经处理 :用户id  ====== >> {}," + "理财产品  ====== >> {}", accno, assetTypeEnum.getName());
            return;
        }
        BigDecimal yesterdayAmount = memYuebaoRecordMapper.yesterdayAmount(accno, assetTypeEnum.getValue(), endTime);
        if (ObjectUtil.isNull(yesterdayAmount)) {
            return;
        }
        if (yesterdayAmount.intValue() < 1) {
            log.error("余额宝派息此用户昨日理财产品无余额 :用户id  ====== >> {}," + "理财产品  ====== >> {}", accno, assetTypeEnum.getName());
            return;
        }
        //用户余额账变
        AssetChangeBO dto = new AssetChangeBO();
        dto.setAccno(accno);
        dto.setAssetCfgId(assetTypeEnum.getValue());
        dto.setBuyAssetCfgId(assetTypeEnum.getValue());
        dto.setChangeAmount(yesterdayAmount);
        dto.setOperateEnum(YueaboOperateEnum.EARN);
        if(assetTypeEnum.getValue().equals(1L)){
            yuebaoChangeService.updateEranYueBao(dto);
        }
        else {
            yuebaoChangeService.updateEranAmount(dto);
        }
    }

    public void saveYuebaoErrorRecord(String accno, String errMsg, AssetTypeEnum assetTypeEnum) {
        try {
            YuebaoDividendFail param = new YuebaoDividendFail();
            param.setAccno(accno);
            param.setAssetCfgId(assetTypeEnum.getValue());
            YuebaoDividendFail rewardFailResult = yuebaoDividendFailMapper.selectOne(param);
            if (ObjectUtil.isNotNull(rewardFailResult)) {
                rewardFailResult.setErrorMsg(errMsg);
                rewardFailResult.setUpdateTime(new Date());
                yuebaoDividendFailMapper.updateByPrimaryKeySelective(rewardFailResult);
            } else {
                YuebaoDividendFail rewardFail = new YuebaoDividendFail();
                rewardFail.setAccno(accno);
                rewardFail.setAssetCfgId(assetTypeEnum.getValue());
                rewardFail.setErrorMsg(errMsg);
                rewardFail.setCreateTime(new Date());
                yuebaoDividendFailMapper.insert(rewardFail);
            }
        } catch (Exception e) {
            log.error("保存任务错误日志出错 ：============》》 {}", e);
        }
    }


}
