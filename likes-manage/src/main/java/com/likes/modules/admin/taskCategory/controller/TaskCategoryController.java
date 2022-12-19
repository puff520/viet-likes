package com.likes.modules.admin.taskCategory.controller;


import com.likes.common.BaseController;
import com.likes.common.annotation.Syslog;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.TaskCategoryRequest;
import com.likes.common.service.task.TaskCategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 任务分类controller
 *
 * @author 泡芙
 */
@RestController
@RequestMapping(value = "/taskCategory")
public class TaskCategoryController extends BaseController {

    private final Logger logger = LogManager.getLogger(getClass());

    @Resource
    private TaskCategoryService taskCategoryService;

    @Syslog("新增任务分类")
    @RequestMapping(name = "新增任务分类", value = "/add", method = RequestMethod.POST)
    public ResultInfo addTask(TaskCategoryRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(taskCategoryService.createTaskCategory(request, loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/taskCategory/add:{}", e.getMessage());
        }
        logger.info("/taskCategory/add{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("任务分类删除")
    @RequestMapping(name = "任务分类删除", value = "/delete", method = RequestMethod.GET)
    public ResultInfo deleteAccount(Integer categoryId) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(taskCategoryService.deleteTaskCategory(categoryId, loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/taskCategory/delete:{}", e.getMessage());
        }
        logger.info("/taskCategory/delete{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @RequestMapping(name = "任务分类详情", value = "/detail", method = RequestMethod.GET)
    public ResultInfo accountDetail(Integer taskId) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(taskCategoryService.TaskCategoryDetail(taskId));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/taskCategory/detail:{}", e.getMessage());
        }
        logger.info("/taskCategory/detail{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("任务分类编辑")
    @RequestMapping(name = "任务分类编辑", value = "/edit", method = RequestMethod.POST)
    public ResultInfo updateAccount(TaskCategoryRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(taskCategoryService.updateTaskCategory(request, loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/taskCategory/edit:{}", e.getMessage());
        }
        logger.info("/taskCategory/edit:{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


    @RequestMapping(name = "任务分类列表", value = "/list", method = RequestMethod.GET)
    public ResultInfo accountList(TaskCategoryRequest request, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(taskCategoryService.taskCategoryList(request, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("taskCategory/list:{}", e.getMessage());
        }
        logger.info("taskCategory/list耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }
}
