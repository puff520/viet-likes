package com.likes.modules.admin.finance.controller;


import com.likes.common.BaseController;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.TraRepayOrderQuery;
import com.likes.modules.admin.finance.service.TraRepayOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/repay")
public class TraRepayOrderController extends BaseController {

    private final Logger logger = LogManager.getLogger(getClass());

    @Resource
    private TraRepayOrderService traRepayOrderService;

    @RequestMapping(name = "代充入款", value = "/order/list", method = RequestMethod.GET)
    public ResultInfo orderList(TraRepayOrderQuery query, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(traRepayOrderService.orderList(query, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/orderList失败:{}", e.getMessage());
        }
        logger.info("/orderList耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

}
