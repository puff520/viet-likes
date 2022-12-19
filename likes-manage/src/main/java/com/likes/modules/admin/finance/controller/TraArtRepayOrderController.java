package com.likes.modules.admin.finance.controller;


import com.likes.common.BaseController;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.TraArtRepayOrderQuery;
import com.likes.common.model.request.TraArtRepayOrderRequest;
import com.likes.modules.admin.finance.service.TraArtRepayOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 代充存提
 */
@RestController
public class TraArtRepayOrderController extends BaseController {

    private final Logger logger = LogManager.getLogger(getClass());

    @Resource
    private TraArtRepayOrderService traArtRepayOrderService;

    @RequestMapping(name = "代充存入提出", value = "/agent/art/transaction", method = RequestMethod.POST)
    public ResultInfo agentArtRepayOrder(TraArtRepayOrderRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(traArtRepayOrderService.agentArtRepayOrder(request, loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/agentArtRepayOrder失败:{}", e.getMessage());
        }
        logger.info("/agentArtRepayOrder耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @RequestMapping(name = "代充存提列表", value = "/agent/tra/list", method = RequestMethod.GET)
    public ResultInfo agentArtRepayOrderList(TraArtRepayOrderQuery query, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(traArtRepayOrderService.agentArtRepayOrderList(query, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/agentArtRepayOrderList失败:{}", e.getMessage());
        }
        logger.info("/agentArtRepayOrderList耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

}
