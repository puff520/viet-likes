package com.likes.modules.admin.finance.controller;

import com.likes.common.BaseController;

import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.AgentTransactionQuery;
import com.likes.common.model.request.AgentTransactionRequest;
import com.likes.modules.admin.finance.service.AgentTransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 代充人入款
 */
@RestController
@RequestMapping(value = "/agent")
public class AgentTransactionController extends BaseController {

    private final Logger logger = LogManager.getLogger(getClass());

    @Resource
    private AgentTransactionService agentTransactionService;


    @RequestMapping(name = "代充人入款", value = "/recharge/list", method = RequestMethod.GET)
    public ResultInfo rechargeList(AgentTransactionQuery query, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(agentTransactionService.rechargeList(query, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/rechargeList失败:{}", e.getMessage());
        }
        logger.info("/rechargeList耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @RequestMapping(name = "代充人入款详情", value = "/recharge/detail", method = RequestMethod.GET)
    public ResultInfo rechargeDetail(Long orderid) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(agentTransactionService.rechargeDetail(orderid));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/rechargeDetail失败:{}", e.getMessage());
        }
        logger.info("/rechargeDetail耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @RequestMapping(name = "代充人入款更新", value = "/recharge/update", method = RequestMethod.POST)
    public ResultInfo updateRecharge(AgentTransactionRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(agentTransactionService.updateRecharge(request, loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/updateRecharge失败:{}", e.getMessage());
        }
        logger.info("/updateRecharge耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }
}
