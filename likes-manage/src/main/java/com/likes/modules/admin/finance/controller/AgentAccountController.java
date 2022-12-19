package com.likes.modules.admin.finance.controller;


import com.likes.common.BaseController;
import com.likes.common.annotation.Syslog;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.AgentAccountRequest;
import com.likes.modules.admin.finance.service.AgentAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 代充银行卡
 */
@RestController
@RequestMapping(value = "/agent/account")
public class AgentAccountController extends BaseController {

    private final Logger logger = LogManager.getLogger(getClass());

    @Resource
    private AgentAccountService agentAccountService;

    @Syslog("代充银行卡新增")
    @RequestMapping(name = "代充银行卡新增", value = "/create", method = RequestMethod.POST)
    public ResultInfo createAccount(AgentAccountRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(agentAccountService.createAccount(request, loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/createAccount失败:{}", e.getMessage());
        }
        logger.info("/createAccount耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("代充银行卡删除")
    @RequestMapping(name = "代充银行卡删除", value = "/delete", method = RequestMethod.POST)
    public ResultInfo deleteAccount(Long bankid) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(agentAccountService.deleteAccount(bankid, loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/deleteAccount失败:{}", e.getMessage());
        }
        logger.info("/deleteAccount耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @RequestMapping(name = "代充银行卡详情", value = "/detail", method = RequestMethod.GET)
    public ResultInfo accountDetail(Long bankid) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(agentAccountService.accountDetail(bankid));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/accountDetail失败:{}", e.getMessage());
        }
        logger.info("/accountDetail耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("代充银行卡编辑")
    @RequestMapping(name = "代充银行卡编辑", value = "/update", method = RequestMethod.POST)
    public ResultInfo updateAccount(AgentAccountRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(agentAccountService.updateAccount(request, loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/updateAccount失败:{}", e.getMessage());
        }
        logger.info("/updateAccount耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("代充银行卡启用禁用")
    @RequestMapping(name = "代充银行卡启用禁用", value = "/status", method = RequestMethod.POST)
    public ResultInfo updateAccountStatus(Long bankid, Integer status) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(agentAccountService.UpdateAccountStatus(bankid, status, loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/updateAccountStatus失败:{}", e.getMessage());
        }
        logger.info("/updateAccountStatus耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @RequestMapping(name = "代充银行卡", value = "/list", method = RequestMethod.GET)
    public ResultInfo accountList(String nickname, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(agentAccountService.accountList(nickname, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/accountList失败:{}", e.getMessage());
        }
        logger.info("/accountList耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }
}
