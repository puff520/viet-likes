package com.likes.modules.admin.task.controller;


import com.likes.common.BaseController;
import com.likes.common.annotation.Syslog;
import com.likes.common.constant.Constants;
import com.likes.common.enums.task.LanguageTypeEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.ReplaceChargeReq;
import com.likes.common.model.request.TaskRequest;
import com.likes.common.mybatis.entity.MemBaseinfo;
import com.likes.common.mybatis.entity.TraOrderinfom;
import com.likes.common.service.task.TaskService;
import com.likes.common.service.vip.VipService;
import com.likes.common.util.DateUtils;
import com.likes.common.util.SnowflakeIdWorker;
import com.likes.common.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 任务controller
 *
 * @author 泡芙
 */
@RestController
@RequestMapping(value = "/task")
public class TaskController extends BaseController {

    private final Logger logger = LogManager.getLogger(getClass());

    @Resource
    private TaskService taskService;
    @Resource
    private VipService vipService;

    @Syslog("新增任务")
    @RequestMapping(name = "新增任务", value = "/add", method = RequestMethod.POST)
    public ResultInfo addTask(@RequestBody TaskRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(taskService.createTask(request, loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/task/add:{}", e.getMessage());
        }
        logger.info("/task/add{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("任务删除")
    @RequestMapping(name = "任务删除", value = "/delete", method = RequestMethod.POST)
    public ResultInfo deleteAccount(String ids) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(taskService.deleteTask(ids, loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/task/delete:{}", e.getMessage());
        }
        logger.info("/task/delete{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


    @RequestMapping(name = "任务详情", value = "/detail", method = RequestMethod.GET)
    public ResultInfo accountDetail(Long taskId) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(taskService.TaskDetail(taskId));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/task/detail:{}", e.getMessage());
        }
        logger.info("/task/detail{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("任务编辑")
    @RequestMapping(name = "任务编辑", value = "/edit", method = RequestMethod.POST)
    public ResultInfo updateAccount(@RequestBody TaskRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(taskService.updateTask(request, loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/task/edit:{}", e.getMessage());
        }
        logger.info("/task/edit:{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


    @RequestMapping(name = "任务列表", value = "/list", method = RequestMethod.GET)
    public ResultInfo list(TaskRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(taskService.taskList(request));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("task/list:{}", e.getMessage());
        }
        logger.info("task/list耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @RequestMapping(name = "语言列表", value = "/LanguageList", method = RequestMethod.GET)
    public ResultInfo LanguageList() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(LanguageTypeEnum.toList());
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("task/LanguageList:{}", e.getMessage());
        }
        logger.info("task/LanguageList耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "购买vip ", value = "/buyVip", method = RequestMethod.POST)
    public ResultInfo buyVip(String accno, Integer levelSeq) {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            response = ResultInfo.ok(vipService.buyVip(accno, levelSeq));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.member/buyVip,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("购买会员出错");
            logger.error("{}.member/buyVip，购买会员出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/member/buyVip，耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


}
