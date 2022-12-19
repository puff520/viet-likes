package com.likes.common.service.task.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.likes.common.annotation.ReadOnlyConnection;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.task.TaskOrderTypeEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.TaskOrderDetail;
import com.likes.common.model.TaskOrderListDetail;
import com.likes.common.model.TaskOrderReviewDetail;
import com.likes.common.model.dto.member.MemGoldchangeDO;
import com.likes.common.model.request.TaskOrderRequest;
import com.likes.common.mybatis.entity.*;
import com.likes.common.mybatis.mapper.TaskMapper;
import com.likes.common.mybatis.mapper.TaskOrderMapper;
import com.likes.common.service.member.MemBaseinfoWriteService;
import com.likes.common.service.member.MemRelationshipService;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.service.task.TaskOrderService;
import com.likes.common.util.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.likes.common.util.ViewUtil.getTradeOffAmount;

@Service
public class TaskOrderServiceImpl implements TaskOrderService {

    private final Logger logger = LogManager.getLogger(getClass());
    @Resource
    private TaskOrderMapper taskOrderMapper;
    @Resource
    private MemBaseinfoWriteService memBaseinfoWriteService;
    @Resource
    private TaskMapper taskMapper;

    @Autowired
    MemRelationshipService memRelationshipService;

    @Override
    public TaskOrderDetail askOrderDetail(Long taskOrderId) {
        TaskOrderDetail taskOrderDetail = new TaskOrderDetail();
        TaskOrder taskOrder = new TaskOrder();
        taskOrder.setId(taskOrderId);
        TaskOrder taskOrderVo = taskOrderMapper.selectByPrimaryKey(taskOrder);

        System.out.println(JSON.toJSONString(taskOrderVo));
        taskOrderDetail.setCreateTime(taskOrderVo.getCreateTime());
        taskOrderDetail.setCreateUser(taskOrderVo.getUpdateUser());
        taskOrderDetail.setMobile(taskOrderVo.getMobile());
        taskOrderDetail.setSubmitSample(taskOrderVo.getSubmitSample());
        Task task = taskMapper.selectTaskById(taskOrderVo.getTaskId());
        taskOrderDetail.setTask(task);
        return taskOrderDetail;
    }

    @Override
    public TaskOrderReviewDetail taskOrderReviewDetail(Long taskOrderId) {
        TaskOrderReviewDetail reviewDetail = taskOrderMapper.selectReviewDetail(taskOrderId);
        return reviewDetail;
    }

    @Override
    @DS("slave")
    public PageInfo<TaskOrderListDetail> taskOrderList(TaskOrderRequest request) {
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<TaskOrderListDetail> list = taskOrderMapper.taskOrderList(request);
        PageInfo<TaskOrderListDetail> pageInfo = new PageInfo(list);
        return pageInfo;
    }




}
