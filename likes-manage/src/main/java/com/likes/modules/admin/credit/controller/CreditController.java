package com.likes.modules.admin.credit.controller;


import cn.hutool.core.util.IdUtil;
import com.likes.common.BaseController;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.constant.RabbitConstants;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.mq.Message;
import com.likes.common.model.mq.MessageType;
import com.likes.common.model.request.CreditRequest;
import com.likes.common.model.request.TaskRequest;
import com.likes.common.mq.producer.MessageProducer;
import com.likes.common.util.MessageBuilder;
import com.likes.modules.admin.credit.service.CreditService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 信用controller
 * @author  泡芙
 */
@RestController
@RequestMapping(value = "/credit")
public class CreditController extends BaseController {

    private final Logger logger = LogManager.getLogger(getClass());

    @Resource
    private CreditService creditService;
    @Resource
    MessageProducer messageProducer;

    @AllowAccess
    @RequestMapping(name = "增加积分", value = "/send", method = RequestMethod.POST)
    public String increase(Long taskId) {
       Map<String, Object> attributes = new HashMap<>();
        attributes.put("taskId",taskId);
        Message message = MessageBuilder.create().withMessageId(IdUtil.simpleUUID())
                .withRoutingKey(RabbitConstants.Key.TASK_KEY)
                .withTopic(RabbitConstants.TASK_TOPIC)
                .withAttributes(attributes)
                .withMessageType(MessageType.RELIANT).build();
        messageProducer.send(message);
        return "ok";

    }


    @RequestMapping(name = "扣除积分", value = "/subtraction", method = RequestMethod.POST)
    public ResultInfo subtraction(CreditRequest request) {
        request.setOperationType(2);
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(creditService.operation(request, loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/credit/subtraction:{}", e.getMessage());
        }
        logger.info("/credit/subtraction{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

}
