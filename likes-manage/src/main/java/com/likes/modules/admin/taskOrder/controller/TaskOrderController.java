package com.likes.modules.admin.taskOrder.controller;


import com.likes.common.BaseController;
import com.likes.common.annotation.Syslog;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.TaskOrderRequest;
import com.likes.common.service.task.TaskOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 任务订单controller
 *
 * @author 泡芙
 */
@RestController
@RequestMapping(value = "/taskOrder")
public class TaskOrderController extends BaseController {

    private final Logger logger = LogManager.getLogger(getClass());

    @Resource
    private TaskOrderService taskOrderService;


    @RequestMapping(name = "任务订单详情", value = "/detail", method = RequestMethod.GET)
    public ResultInfo taskOrderDetail(Long taskOrderId) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(taskOrderService.askOrderDetail(taskOrderId));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/taskOrder/detail:{}", e.getMessage());
        }
        logger.info("/taskOrder/detail{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


    @RequestMapping(name = "任务订单审核详情", value = "/reviewDetail", method = RequestMethod.GET)
    public ResultInfo reviewDetail(Long taskOrderId) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(taskOrderService.taskOrderReviewDetail(taskOrderId));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/taskOrder/detail:{}", e.getMessage());
        }
        logger.info("/taskOrder/detail{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


//    @Syslog("任务订单编辑")
//    @RequestMapping(name = "任务订单编辑", value = "/edit", method = RequestMethod.POST)
//    public ResultInfo updateTaskOrder(@RequestBody TaskOrderRequest request) {
//        long start = System.currentTimeMillis();
//        ResultInfo response = ResultInfo.ok();
//        try {
//            LoginUser loginUser = getLoginAdmin();
//            response.setData(taskOrderService.updateTaskOrder(request, loginUser));
//        } catch (BusinessException e) {
//            response.setResultInfo(e.getCode(), e.getMessage());
//            logger.info("/taskOrder/edit:{}", e.getMessage());
//        }
//        logger.info("/taskOrder/edit:{}毫秒：", (System.currentTimeMillis() - start));
//        return response;
//    }




    @RequestMapping(name = "任务订单列表", value = "/list", method = RequestMethod.GET)
    public ResultInfo orderList(TaskOrderRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(taskOrderService.taskOrderList(request));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("taskOrder/list:{}", e.getMessage());
        }
        logger.info("taskOrder/list耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }
}
