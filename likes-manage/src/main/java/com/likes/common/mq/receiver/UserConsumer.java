package com.likes.common.mq.receiver;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.likes.common.constant.RabbitConstants;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.dto.member.MemGoldchangeDO;
import com.likes.common.model.response.level.MemberLevelResponse;
import com.likes.common.mq.producer.brokery.RabbitBrokerImpl;
import com.likes.common.mybatis.entity.*;
import com.likes.common.mybatis.mapper.TaskMapper;
import com.likes.common.mybatis.mapper.TaskOrderMapper;
import com.likes.common.mybatis.mapper.TaskRewardFailMapper;
import com.likes.common.mybatis.mapperext.member.MemLevelConfigMapperExt;
import com.likes.common.service.member.MemBaseinfoWriteService;
import com.likes.common.service.member.MemLevelConfigService;
import com.likes.common.service.member.MemRelationshipService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.CommonUtils;
import com.likes.common.util.StringUtils;
import com.likes.common.util.redis.RedisBaseUtil;
import com.rabbitmq.client.Channel;
import com.uduncloud.sdk.domain.Trade;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Component
public class UserConsumer {

    protected static Logger log = LoggerFactory.getLogger(UserConsumer.class);

    private static final ExecutorService taskCachedThreadPool = CommonUtils.getMaxThreadPoolExecutor();

    @Resource
    private MemLevelConfigMapperExt memLevelConfigMapperExt;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private TaskOrderMapper taskOrderMapper;
    @Resource
    private MemBaseinfoWriteService memBaseinfoWriteService;
    @Resource
    private SysParamService sysParamService;
    @Resource
    private MemLevelConfigService memLevelConfigService;
    @Resource
    MemRelationshipService memRelationshipService;
    @Resource
    TaskRewardFailMapper taskRewardFailMapper;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = RabbitConstants.Queue.TASK_QUEUE,
            durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = RabbitConstants.TASK_TOPIC),
            key = {RabbitConstants.Key.TASK_KEY}))
    @Transactional
    public void upLevelMessage(Message message, Channel channel) {
        Long taskId = null;
        try {
            String messageId = message.getMessageProperties().getMessageId();
            String payload = new String(message.getBody());
            String cacheMessageId = RedisBaseUtil.get(RedisKeys.SYS_RABBITMQ_MSG_ID_KEY + messageId);
            if (messageId.equals(cacheMessageId)) {
                log.warn("该消息已经被消费  ====== {}", message);
                return;

            }
            com.likes.common.model.mq.Message myMsg = JSONUtil.toBean(payload, com.likes.common.model.mq.Message.class);
            taskId = Long.parseLong(myMsg.getAttributes().get("taskId").toString());
            autoReview(taskId);
            Long deliveryTag = message.getMessageProperties().getDeliveryTag();
            //手工ACK
            channel.basicAck(deliveryTag, false);
            RedisBaseUtil.set(RedisKeys.SYS_RABBITMQ_MSG_ID_KEY + messageId, messageId, 60 * 60L);
        } catch (Exception e) {
            log.error("执行自动审核队列错误 ：============》》 {}", e);
            final Long finalTaskId = taskId;
            taskCachedThreadPool.execute(() -> saveTaskErrorRecord(finalTaskId, e.getMessage()));
            throw new RuntimeException("用户修改余额出错,队列事务回滚", e.getCause());
        }
    }


    public void autoReview(Long orderId) {
        TaskOrder taskOrderReq = new TaskOrder();
        taskOrderReq.setStatus(2);
        taskOrderReq.setId(orderId);
        TaskOrder taskOrderVo = taskOrderMapper.selectOne(taskOrderReq);
        if (ObjectUtil.isNull(taskOrderVo)) {
            log.error("没有找到该条任务订单 :订单id  ====== >> {}", orderId);
            return;
        }
        //用户余额账变
        MemGoldchangeDO dto = new MemGoldchangeDO();
        dto.setOpnote("任务奖励");
        if(ObjectUtil.isNotNull(taskOrderVo.getReceivePrice())){
           dto.setQuantity(taskOrderVo.getReceivePrice());
        }else {
            Task task = taskMapper.selectTaskById(taskOrderVo.getTaskId());
            if(ObjectUtil.isNotNull(task.getPrice())){
                dto.setQuantity(task.getPrice());
            }else {
                MemLevelConfig memLevelConfig =  memLevelConfigMapperExt.selectMemLevel(taskOrderReq.getMemNo());
                dto.setQuantity(memLevelConfig.getPromoteAmount());
            }
        }
        dto.setCreatTime(new Date());
        dto.setAccno(taskOrderVo.getMemNo());
        dto.setChangetype(GoldchangeEnum.TASK_AWARD.getValue());
        boolean flag = memBaseinfoWriteService.updateUserBalance(dto);
        if (!flag) {
            taskOrderVo.setStatus(4);
            taskOrderVo.setUpdateTime(new Date());
            taskOrderMapper.updateByPrimaryKeySelective(taskOrderVo);
            log.error("执行自动审核定时任务订单处理失败 ：============》》 {}", orderId);
        } else {
            taskOrderVo.setStatus(3);
            taskOrderVo.setUpdateTime(new Date());
            taskOrderMapper.updateByPrimaryKeySelective(taskOrderVo);
        }
        MemberLevelResponse response = memLevelConfigService.getMemLevelConfig(taskOrderVo.getMemNo());
        if (response.equals(null) || response.getLevelSeq() < 1) {
            return;
        }
        returnBrokerage(taskOrderVo.getMemNo(), dto.getQuantity());
    }


    public void returnBrokerage(String memNo, BigDecimal amount) {
        SysParameter rebate1 = this.sysParamService.getByCode("rebate1");
        if (rebate1 == null || StringUtils.isBlank(rebate1.getSysparamvalue())) {
            return;
        }
        SysParameter rebate2 = this.sysParamService.getByCode("rebate2");
        if (rebate2 == null || StringUtils.isBlank(rebate2.getSysparamvalue())) {
            return;
        }
        SysParameter rebate3 = this.sysParamService.getByCode("rebate3");
        if (rebate3 == null || StringUtils.isBlank(rebate3.getSysparamvalue())) {
            return;
        }
        String startParam = String.format("%s,%s,%s", rebate1.getSysparamvalue(), rebate2.getSysparamvalue(), rebate3.getSysparamvalue());
        String accno = memNo;
        String[] vals = startParam.split(",");
        for (int i = 0; i < vals.length; i++) {
            String str = vals[i].trim();
            if (!NumberUtils.isNumber(str)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_165.getCode(), "等级配置有误");
            }
            // 只有三级返佣
            if (i == 3) {
                return;
            }
            MemRelationship b = memRelationshipService.findByAccno(accno);
            if (b == null || b.getRefaccno().equals("ROOT")) {
                return;
            }

            accno = b.getRefaccno();
            BigDecimal rate = NumberUtils.createBigDecimal(str);
            BigDecimal brokerageMoney = amount.multiply(rate).divide(BigDecimal.valueOf(100));

            MemberLevelResponse response = memLevelConfigService.getMemLevelConfig(accno);
            if (response.equals(null) || response.getLevelSeq() < 1) {
                continue;
            }
            MemGoldchangeDO change = new MemGoldchangeDO();
            change.setAccno(accno);
            change.setShowChange(brokerageMoney);
            change.setQuantity(brokerageMoney);
            if (i == 0) {
                change.setChangetype(GoldchangeEnum.BROKERAGE_LEVEL_1.getValue());
            } else if (i == 1) {
                change.setChangetype(GoldchangeEnum.BROKERAGE_LEVEL_2.getValue());
            } else if (i == 2) {
                change.setChangetype(GoldchangeEnum.BROKERAGE_LEVEL_3.getValue());
            }
            change.setOpnote("用户:【" + b.getAccno() + "】 完成任务【" + memNo + "】,返佣奖励" + change.getQuantity());
            memBaseinfoWriteService.updateUserBalance(change);

        }

    }


    public void saveTaskErrorRecord(Long taskId, String errMsg) {
        try {
            TaskRewardFail param = new TaskRewardFail();
            param.setTaskId(taskId);
            TaskRewardFail rewardFailResult = taskRewardFailMapper.selectOne(param);
            if (ObjectUtil.isNotNull(rewardFailResult)) {
                rewardFailResult.setUpdateTime(new Date());
                taskRewardFailMapper.updateByPrimaryKeySelective(rewardFailResult);
            } else {
                TaskRewardFail rewardFail = new TaskRewardFail();
                rewardFail.setTaskId(taskId);
                rewardFail.setErrorMsg(errMsg);
                rewardFail.setCreateTime(new Date());
                taskRewardFailMapper.insert(rewardFail);
            }
        } catch (Exception e) {
            log.error("保存任务错误日志出错 ：============》》 {}", e);
        }
    }


}
