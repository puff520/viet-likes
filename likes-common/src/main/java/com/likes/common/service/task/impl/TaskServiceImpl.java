package com.likes.common.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.TaskDetail;
import com.likes.common.model.dto.member.MemGoldchangeDO;
import com.likes.common.model.request.TaskRequest;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.MemLevelConfig;
import com.likes.common.mybatis.entity.Task;
import com.likes.common.mybatis.entity.TaskOrder;
import com.likes.common.mybatis.mapper.TaskMapper;
import com.likes.common.mybatis.mapper.TaskOrderMapper;
import com.likes.common.mybatis.mapperext.member.MemLevelConfigMapperExt;
import com.likes.common.service.member.MemBaseinfoService;
import com.likes.common.service.member.MemBaseinfoWriteService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.service.task.TaskService;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.DateUtils;
import com.likes.common.util.RandomValue;
import com.likes.common.util.StringUtils;
import com.likes.common.util.http.HttpRespons;
import com.likes.common.util.http.HttpUtils;
import com.likes.common.util.redis.RedisBaseUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskMapper taskMapper;
    @Resource
    private TaskOrderMapper taskOrderMapper;
    @Resource
    private MemBaseinfoWriteService memBaseinfoWriteService;
    @Resource
    private SysParamService sysParamService;
    @Resource
    private MemBaseinfoService memBaseinfoService;

    @Resource
    private MemLevelConfigMapperExt memLevelConfigMapperExt;


    @Override
    @Transactional
    public boolean createTask(TaskRequest request, LoginUser loginUser) {
        if (CollectionUtil.isEmpty(request.getTaskLevel())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_135.getCode(), "必选选择一个任务等级！");
        }
        Task task = new Task();
        BeanUtils.copyProperties(request, task);
        task.setCreateTime(new Date());
        task.setApieceNum(1);
        task.setCreateUser(loginUser.getAccno());
        task.setSurplusNum(request.getMaxNum());
        task.setFinishDate(DateUtils.getDayStartSecond(request.getFinishDate()));
        task.setCreateMobile(loginUser.getPhoneno());
        task.setCreateUser(RandomValue.getTel());
        task.setTaskLevelIds(StringUtils.join(request.getTaskLevel(), ","));
        task.setSendSdkUrl(task.getTaskUrl());
        int row = taskMapper.insertSelective(task);
        return row > 0;
    }


    @Override
    public boolean deleteTask(String ids, LoginUser loginUser) {
        String[] idArray = ids.split(",");
        List<String> strList = Stream.of(idArray).collect(Collectors.toList());
        List<Integer> idList = strList.stream().map(Integer::parseInt).collect(Collectors.toList());
        Example example = new Example(Task.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", idList);
        int row = taskMapper.deleteByExample(example);
        return row > 0;
    }

    @Override
    public boolean updateTask(TaskRequest request, LoginUser loginUser) {
        Task task = new Task();
        BeanUtils.copyProperties(request, task);
        if (request.getFinishDate() != null) {
            task.setFinishDate(DateUtils.getDayStartSecond(request.getFinishDate()));
        }
        if (CollectionUtil.isEmpty(request.getTaskLevel())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_135.getCode(), "必选选择一个任务等级！");
        }
//        if (request.getId() > 2457) {
            task.setTaskLevelIds(StringUtils.join(request.getTaskLevel(), ","));
//        }
        task.setCreateUser(null);
        return taskMapper.updateByPrimaryKeySelective(task) > 0;
    }

    @Override
    public boolean openLink(Long taskOrderId, LoginUser loginUser) {
        RedisBaseUtil.set("openLink:" + loginUser.getAcclogin() + taskOrderId, 2, 60 * 60 * 60 * 24L);
        return true;
    }

    @Override
    public TaskDetail TaskDetail(Long taskId) {
        Task task = new Task();
        task.setId(taskId);
        Task taskVo = taskMapper.selectByPrimaryKey(task);
        TaskDetail taskDetail = new TaskDetail();
        BeanUtils.copyProperties(taskVo, taskDetail);
        return taskDetail;
    }

    @Override
    public PageInfo<TaskDetail> taskList(TaskRequest request) {
        if (request.getStatus() != null) {
            if (request.getStatus().equals(-1)) {
                request.setStatusStr("-1");
            }
            if (request.getStatus().equals(1)) {
                request.setStatusStr("1");
            }
            if (StringUtils.isNotBlank(request.getStatusStr())) {
                request.setStatus(null);
            }
        }

        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<TaskDetail> list = taskMapper.selectTaskList(request);
        list.forEach(item -> {
            if (item.getStatus().equals(1)) {
                if (item.getFinishDate() != null && item.getFinishDate().before(new Date())) {
                    item.setStatus(-1);
                }
            }
            String[] arr = item.getTaskLevelIds().split(",");
            List<String> strList = Arrays.asList(arr);
            List<Integer> intList = strList.stream().map(Integer::parseInt).collect(Collectors.toList());
            item.setTaskLevel(intList);
        });
        PageInfo<TaskDetail> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    public static String getRandom() {
        Random random = new Random();
        String fourRandom = random.nextInt(100000) + "";
        int randLength = fourRandom.length();
        if (randLength < 5) {
            for (int i = 1; i <= 5 - randLength; i++) {
                fourRandom = "0" + fourRandom;
            }
        }
        return fourRandom;
    }


}
