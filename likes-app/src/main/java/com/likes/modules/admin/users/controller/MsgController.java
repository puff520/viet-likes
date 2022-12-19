package com.likes.modules.admin.users.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.InfSysnoticeRequest;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.util.LogUtils;
import com.likes.modules.admin.users.service.MsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 消息中心
 *
 * @author bjkj
 */
@Controller
@RequestMapping(value = "/msg")
public class MsgController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private MsgService msgService;

    @ResponseBody
    @RequestMapping(name = "消息数量", value = "/myMsgNum", method = RequestMethod.GET)
    public ResultInfo myMsgNum() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(msgService.myMsgNum(loginUserAPP.getAccno()));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.myMsgNum 失败:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("获取消息数量出错");
            logger.error("{}.myMsgNum 出错:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/myMsgNum耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "资源审核消息", value = "/mySourceMsgList", method = RequestMethod.GET)
    public ResultInfo mySourceMsgList(UsersRequest usersRequest) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            PageBounds page = new PageBounds();
            page.setPageNo(usersRequest.getPageNo());
            page.setPageSize(usersRequest.getPageSize());
            usersRequest.setAccno(loginUserAPP.getAccno());
            response.setData(msgService.mySourceMsgList(page, usersRequest));
            LogUtils.logUserModifyOperates(getClass().getName() + ".mySourceMsgList", usersRequest, loginUserAPP);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.mySourceMsgList 失败:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(usersRequest), e);
        } catch (Exception e) {
            response = ResultInfo.fail("资源审核消息出错");
            logger.error("{}.mySourceMsgList 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(usersRequest), e);
        }
        logger.info("/mySourceMsgList耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "系统公告消息", value = "/systemMsgList", method = RequestMethod.GET)
    public ResultInfo systemMsgList(UsersRequest usersRequest) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            PageBounds page = new PageBounds();
            page.setPageNo(usersRequest.getPageNo());
            page.setPageSize(usersRequest.getPageSize());
            usersRequest.setAccno(loginUserAPP.getAccno());
            response.setData(msgService.systemMsgList(page, usersRequest));
            LogUtils.logUserModifyOperates(getClass().getName() + ".systemMsgList", usersRequest, loginUserAPP);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.systemMsgList 失败:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(usersRequest), e);
        } catch (Exception e) {
            response = ResultInfo.fail("获取系统公告消息出错");
            logger.error("{}.systemMsgList 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(usersRequest), e);
        }
        logger.info("/systemMsgList耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @AllowAccess
    @ResponseBody
    @RequestMapping(name = "APP公告消息", value = "/InfSysnoticeList", method = RequestMethod.GET)
    public ResultInfo infSysnoticeList(InfSysnoticeRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(msgService.infSysnoticeList(req));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.InfSysnoticeList 失败:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.fail("获取APP公告消息出错");
            logger.error("{}.InfSysnoticeList 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/InfSysnoticeList耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @AllowAccess
    @ResponseBody
    @RequestMapping(name = "三个公告消息", value = "/InfSysnoticeMsgList", method = RequestMethod.GET)
    public ResultInfo sys(InfSysnoticeRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(msgService.infSysnoticeMsgList(req));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.InfSysnoticeMsgList 失败:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.fail("获取三个公告消息出错");
            logger.error("{}.InfSysnoticeMsgList 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/InfSysnoticeMsgList{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

}
