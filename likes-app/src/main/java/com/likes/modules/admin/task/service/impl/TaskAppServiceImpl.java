package com.likes.modules.admin.task.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.likes.common.annotation.ReadOnlyConnection;
import com.likes.common.constant.Constants;
import com.likes.common.enums.CreditChangeEnum;
import com.likes.common.enums.GoldchangeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.task.TaskOrderTypeEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.*;
import com.likes.common.model.dto.member.MemGoldchangeDO;
import com.likes.common.model.request.*;
import com.likes.common.model.response.level.MemberLevelResponse;
import com.likes.common.mybatis.entity.*;
import com.likes.common.mybatis.mapper.TaskCategoryMapper;
import com.likes.common.mybatis.mapper.TaskMapper;
import com.likes.common.mybatis.mapper.TaskOrderMapper;
import com.likes.common.mybatis.mapperext.member.MemBaseinfoMapperExt;
import com.likes.common.mybatis.mapperext.member.MemLevelConfigMapperExt;
import com.likes.common.service.credit.MemCreditService;
import com.likes.common.service.member.MemBaseinfoWriteService;
import com.likes.common.service.member.MemLevelConfigService;
import com.likes.common.service.member.MemLevelService;
import com.likes.common.service.member.MemRelationshipService;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.service.task.TaskOrderService;
import com.likes.common.util.CollectionUtil;
import com.likes.common.util.CommonUtils;
import com.likes.common.util.DateUtils;
import com.likes.common.util.StringUtils;
import com.likes.common.util.http.HttpRespons;
import com.likes.common.util.http.HttpUtils;
import com.likes.common.util.redis.RedisBaseUtil;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.task.service.TaskAppService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import static com.likes.common.util.ViewUtil.getTradeOffAmount;

@Service
@Log4j2
public class TaskAppServiceImpl implements TaskAppService {

    @Resource
    private TaskMapper taskMapper;
    @Resource
    private TaskOrderMapper taskOrderMapper;
    @Resource
    private TaskCategoryMapper taskCategoryMapper;
    @Resource
    private MemLevelConfigMapperExt memLevelConfigMapperExt;
    @Resource
    private MemBaseinfoMapperExt memBaseinfoMapperExt;
    @Resource
    private MemBaseinfoWriteService memBaseinfoWriteService;
    @Resource
    private MemCreditService memCreditService;
    @Resource
    private SysBusParamService sysBusParamService;
    @Autowired
    MemRelationshipService memRelationshipService;
    @Resource
    private MemLevelConfigService memLevelConfigService;
    @Resource
    private SysParamService sysParamService;

    @Override
    @DS("slave")
    public PageInfo<TaskAppDto> taskAppList(TaskRequest request, LoginUser loginUser) {
        Integer xyf = memCreditService.selectCreditByMemNo(loginUser.getAccno());
        if (xyf < 60) {
            PageInfo<TaskAppDto> pageInfo = new PageInfo<>(new ArrayList<>(0));
            return pageInfo;
        }
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<TaskAppDto> list = taskMapper.selectTaskAppList(request);
        MemLevelConfig memLevelConfig = memLevelConfigMapperExt.selectMemLevel(request.getAccno());
        list.forEach(item -> {
            item.setPrice(memLevelConfig.getPromoteAmount());
            item.setLevelSeq(memLevelConfig.getLevelSeq());
            item.setLevel(memLevelConfig.getLevel());
        });
        PageInfo<TaskAppDto> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<TaskAppDto> myPubtaskList(TaskRequest request) {

        return null;
    }

    @Override
    @DS("slave")
    public TaskAppDetail taskAppDetail(Long taskId) {
        TaskAppDetail taskAppDetail = taskMapper.selectTaskAppDetail(taskId);
        int num = taskOrderMapper.completeTaskOrderNumById(taskId);
        MemBaseinfo memBaseinfo = memBaseinfoMapperExt.getUserByPhone(taskAppDetail.getCreateUser());
        taskAppDetail.setCompleteTaskNum(num);
        if (taskAppDetail.getTaskSource().equals(1)) {
            taskAppDetail.setHeadimg(memBaseinfo.getHeadimg());
        } else {

        }
        return taskAppDetail;
    }


    @Override
    @DS("slave")
    public List<TaskOrderDetail> queryMemberOrderList(LoginUser loginUser, TaskOrderRequest request) {
        List<TaskOrderDetail> details = new LinkedList<>();
        TaskOrder taskOrder = new TaskOrder();
        taskOrder.setStatus(request.getStatus());
        taskOrder.setMemNo(loginUser.getAccno());
        List<TaskOrder> list = taskOrderMapper.select(taskOrder);
        if (CollectionUtil.isEmpty(list)) {
            return details;
        }
        List<Task> taskList = taskList(list);
        List<TaskCategory> taskCategoryList = taskCategoryList();
        list.forEach(item -> {
            TaskOrderDetail detail = new TaskOrderDetail();
            BeanUtils.copyProperties(item, detail);
            detail.setTaskOrderId(item.getId());
            detail.setRemark(item.getRemark());
            detail.setTaskType(item.getTaskType());
            detail.setUpdateTime(item.getUpdateTime());
            Integer integer = RedisBaseUtil.get("openLink:" + loginUser.getAcclogin() + item.getId());
            if (ObjectUtil.isNotNull(integer)) {
                detail.setTaskType(3);
            }
            boolean levelOldFlag = true;
            if (ObjectUtil.isNotNull(item.getReceivePrice())) {
                detail.setUsdt(item.getReceivePrice());
                levelOldFlag = false;
            }
            boolean finalLevelOldFlag = levelOldFlag;
            taskList.forEach(task -> {
                if (task.getId().equals(item.getTaskId())) {
                    detail.setTask(task);
                    if (finalLevelOldFlag) {
                        detail.setUsdt(task.getPrice());
                    }
                }
            });
            taskCategoryList.forEach(taskCategory -> {
                if (detail.getTask().getCategoryId().equals(taskCategory.getId())) {
                    if (StringUtils.isNotBlank(request.getLanguage())) {
                        if (request.getLanguage().equals("en")) {
                            taskCategory.setName(taskCategory.getEnName());
                            taskCategory.setDescription(taskCategory.getEnDescription());
                        } else if (request.getLanguage().equals("sp")) {
                            taskCategory.setName(taskCategory.getSpName());
                            taskCategory.setDescription(taskCategory.getSpDescription());
                        } else if (request.getLanguage().equals("ab")) {
                            taskCategory.setName(taskCategory.getAbName());
                            taskCategory.setDescription(taskCategory.getAbDescription());
                        } else if (request.getLanguage().equals("fn")) {
                            taskCategory.setName(taskCategory.getFnName());
                            taskCategory.setDescription(taskCategory.getFnDescription());
                        } else if (request.getLanguage().equals("in")) {
                            taskCategory.setName(taskCategory.getInName());
                            taskCategory.setDescription(taskCategory.getInDescription());
                        }
                    }
                    detail.setCategoryName(taskCategory.getName());
                    detail.setCategoryDescription(taskCategory.getDescription());
                    detail.setIcon(taskCategory.getIcon());
                }
            });
            details.add(detail);
        });

        // 获取充值方式


        return details;
    }


    public List<TaskCategory> taskCategoryList() {
        List<TaskCategory> taskCategories = taskCategoryMapper.selectAll();
        return taskCategories;
    }


    public List<Task> taskList(List<TaskOrder> taskOrderList) {
        List<Long> taskIds = taskOrderList.stream().map(TaskOrder::getTaskId).collect(Collectors.toList());
        Example example = new Example(Task.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", taskIds);
        List<Task> taskList = taskMapper.selectByExample(example);
        return taskList;
    }


    @Override
    public PageInfo<WaitReceiveDto> waitReceiveList(LoginUser LoginUser, Integer status, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<WaitReceiveDto> list = taskOrderMapper.waitReceiveList(LoginUser.getMobileno(), status);
        list.forEach(item -> {
            MemLevelConfig memLevelConfig = memLevelConfigMapperExt.selectMemLevelBySeq(Integer.parseInt(item.getTaskLevel()));
            item.setTaskLevel(memLevelConfig.getLevel());
            int num = taskOrderMapper.completeTaskOrderNumById(item.getTaskId());
            item.setCompleteTaskNum(num);
        });
        PageInfo<WaitReceiveDto> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean reviceTask(LoginUser loginUser, ReceiveTaskRequest request) {
        Integer xyf = memCreditService.selectCreditByMemNo(loginUser.getAccno());
        if (xyf < 60) {
            throw new BusinessException(StatusCode.LIVE_ERROR_160.getCode(), "信誉分太低,不能进行该操作！");
        }
        Task taskReq = new Task();
        taskReq.setId(request.getTaskId());
        Task taskVo = taskMapper.selectOne(taskReq);
        if (taskVo == null) {
            throw new BusinessException(StatusCode.LIVE_ERROR_130.getCode(), "任务不存在！");
        }
        if (taskVo.getMaxNum().equals(taskVo.getApplyNum())) {
            throw new BusinessException(StatusCode.LIVE_ERROR_131.getCode(), "任务已被领取完！");
        }
        int count = taskOrderMapper.counMemTaskOrder(request.getTaskId(), loginUser.getAccno());
        if (count > taskVo.getApieceNum()) {
            throw new BusinessException(StatusCode.LIVE_ERROR_132.getCode(), "该任务每人限领" + taskVo.getApieceNum() + "次");
        }
        MemLevelConfig levelConfig = memLevelConfigMapperExt.selectMemLevel(loginUser.getAccno());
        if (!taskVo.getTaskLevelIds().contains(levelConfig.getLevelSeq() + "")) {
            throw new BusinessException(StatusCode.LIVE_ERROR_133.getCode(), "会员等级不符合！");
        }
        int todayCount = taskOrderMapper.counMemTodayTaskOrder(loginUser.getAccno(), DateUtils.dayBeginStr(), DateUtils.dayEndStr());
        Integer taskNum = levelConfig.getDoTaskTimes();
        if (todayCount >= taskNum) {
            throw new BusinessException(StatusCode.LIVE_ERROR_134.getCode(), "您今天领取的任务已超限！");
        }
        MemLevelConfig memLevelConfig = memLevelConfigMapperExt.selectMemLevel(loginUser.getAccno());
        TaskOrder taskOrder = new TaskOrder();
        taskOrder.setMemNo(loginUser.getAccno());
        taskOrder.setCreateTime(new Date());
        taskOrder.setUpdateTime(new Date());
        taskOrder.setOrderNo(getOrderNo(loginUser));
        taskOrder.setCreateUser(loginUser.getAccno());
        taskOrder.setMobile(loginUser.getMobileno());
        taskOrder.setTaskId(request.getTaskId());
        taskOrder.setReceivePrice(memLevelConfig.getPromoteAmount());
        taskOrder.setReceiveLevel(memLevelConfig.getLevel());
        taskOrder.setStatus(1);
        int row = taskOrderMapper.insertSelective(taskOrder);
        if (row > 0) {
            int taskRow = taskMapper.updateApplyNum(request.getTaskId());
            if (taskRow > 0) {
                if (taskVo.getMaxNum().equals(taskVo.getApplyNum() + 1)) {
                    taskMapper.updateStatus(request.getTaskId());
                }
                reviceTaskCredit(taskOrder, request);
                return true;
            } else {
                throw new BusinessException(StatusCode.LIVE_ERROR_135.getCode(), "领取任务失败！");
            }
        }
        return false;
    }

    @Override
    @Transactional
    public boolean submitTask(LoginUser loginUser, SubmitTaskRequest request) {
        Integer xyf = memCreditService.selectCreditByMemNo(loginUser.getAccno());
        if (xyf < 60) {
            throw new BusinessException(StatusCode.LIVE_ERROR_160.getCode(), "信誉分太低,不能进行该操作！");
        }
        TaskOrder taskOrderReq = new TaskOrder();
        taskOrderReq.setId(request.getTaskOrderId());
        TaskOrder taskOrderVo = taskOrderMapper.selectOne(taskOrderReq);
        if (taskOrderVo == null) {
            throw new RuntimeException("获取订单详情失败");
        }
        if (taskOrderVo.getStatus() != TaskOrderTypeEnum.UNDERWAY.getValue()) {
            throw new RuntimeException("订单状态异常");
        }
        autoReview(taskOrderVo);
        return true;
    }


    public void autoReview(TaskOrder taskOrder) {
        //用户余额账变
        MemGoldchangeDO dto = new MemGoldchangeDO();
        dto.setOpnote("任务奖励");
        if (ObjectUtil.isNotNull(taskOrder.getReceivePrice())) {
            dto.setQuantity(taskOrder.getReceivePrice());
        } else {
            Task task = taskMapper.selectTaskById(taskOrder.getTaskId());
            if (ObjectUtil.isNotNull(task.getPrice())) {
                dto.setQuantity(task.getPrice());
            } else {
                MemLevelConfig memLevelConfig = memLevelConfigMapperExt.selectMemLevel(taskOrder.getMemNo());
                dto.setQuantity(memLevelConfig.getPromoteAmount());
            }
        }
        dto.setCreatTime(new Date());
        dto.setAccno(taskOrder.getMemNo());
        dto.setChangetype(GoldchangeEnum.TASK_AWARD.getValue());
        boolean flag = memBaseinfoWriteService.updateUserBalance(dto);
        if (!flag) {
            taskOrder.setStatus(4);
            taskOrder.setUpdateTime(new Date());
            taskOrderMapper.updateByPrimaryKeySelective(taskOrder);
            log.error("提交时任务订单处理失败 ：============》》 {}", taskOrder.getTaskId());
        } else {
            taskOrder.setStatus(3);
            taskOrder.setSendStatus(1);
            taskOrder.setUpdateTime(new Date());
            taskOrderMapper.updateByPrimaryKeySelective(taskOrder);
        }
        MemberLevelResponse response = memLevelConfigService.getMemLevelConfig(taskOrder.getMemNo());
        if (response.equals(null) || response.getLevelSeq() < 1) {
            return;
        }
        returnBrokerage(taskOrder.getMemNo(), dto.getQuantity());
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


    public static String getOrderNo(LoginUser loginUser) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        int random = (int) ((Math.random() * 9 + 1) * 1000);
        return loginUser.getMemid() + newDate + random;
    }


    public void reviceTaskCredit(TaskOrder taskOrder, ReceiveTaskRequest request) {
        CreditRequest creditRequest = new CreditRequest();
        SysBusparameter sysBusparameter = sysBusParamService.selectByBusparamcode("d_init");
        if (sysBusparameter != null && StringUtils.isNotBlank(sysBusparameter.getBusparamname())) {
            creditRequest.setIntegral(Integer.parseInt(sysBusparameter.getBusparamname()));
        } else {
            creditRequest.setIntegral(0);
        }
        if (creditRequest.getIntegral().equals(0)) {
            return;
        }
        creditRequest.setOperationType(2);
        creditRequest.setMemNo(taskOrder.getMemNo());
        creditRequest.setSource(CreditChangeEnum.TASK.getName());
        memCreditService.operation(creditRequest);

    }


    public static int zfsjs() {
        int randomNumber = (int) Math.round(Math.random() * 20) + 0;//0-20
        int type = (int) Math.round(Math.random() * 1);//1 整数，0负数bai
        if (type == 0) {
            randomNumber = 0 - randomNumber;
        }
        int zfNumber = randomNumber * 1000;
        return zfNumber;
    }

    public String getSdkUrl(String url) {
        if (url.contains("v.douyin.com")) {
            return getkDouUrl(url);
        } else if (url.contains("kuaishou.com")) {
            return getkwaiUrl(url);
        } else {
            return null;
        }
    }


    public String getkDouUrl(String url) {
        HttpRespons respons = getHttpRespone(url);
        String path = respons.path;
        if (StringUtils.isBlank(path)) {
            return url;
        }
        int first = path.lastIndexOf("/");
        int lastSecondIndex = path.lastIndexOf("/", first - 1);
        String code = path.substring(lastSecondIndex + 1, first);
        String sendTemplete = "snssgdk1128://aweme/detail/{0}?refer=web&gd_label=click_wap_detail_download_feature&appParam=&needlaunchlog=1";
        String sendSdkUrl = sendTemplete.replace("{0}", code);
        return sendSdkUrl;
    }


    public String getkwaiUrl(String url) {
        HttpRespons respons = getHttpRespone(url);
        String path = respons.file;
        if (StringUtils.isBlank(path)) {
            return url;
        }
        int first = path.indexOf("&shareObjectId=");
        int last = path.indexOf("&shareUrlOpened=");
        String code = path.substring(first + 15, last);
        String sendTemplete = "kwai://work/{0}";
        String sendSdkUrl = sendTemplete.replace("{0}", code);
        return sendSdkUrl;
    }


    public HttpRespons getHttpRespone(String url) {
        HttpRespons respone = null;
        try {
            respone = HttpUtils.sendGet(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respone;
    }


}
