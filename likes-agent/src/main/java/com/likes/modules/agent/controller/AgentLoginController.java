package com.likes.modules.agent.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.util.LogUtils;
import com.likes.modules.agent.service.AgentLoginService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * @author abu
 * @version 1.0
 * @Description 运营登录
 * @revise
 * @time 2018年6月8日 下午3:25:23
 */
@RestController
@RequestMapping(value = "/agentLogin")
public class AgentLoginController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private AgentLoginService loginService;

    @AllowAccess
    @ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(name = "登录", value = "/login", method = RequestMethod.POST)
    public ResultInfo login(UsersRequest req, HttpServletRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        LoginUser loginUser = null;
        try {
            String source = resolveClientType(request);
            req.setClintipadd(resolveIp(request));
            req.setServerIp(getServerIp());
            req.setClientPhoneModel(resolveClientPhoneModel(request));
            loginUser = loginService.login(req, source);
            response.setData(loginUser);
            logger.info("{}BB直播app：{},登录成功. result:{}", req.getEmail(), JSONObject.toJSONString(req), JSONObject.toJSONString(loginUser));
            LogUtils.logUserModifyOperates(this.getClass().getName() + ".login", req, getLoginUserAPP());
        } catch (BusinessException e) {
            logger.error("{}.login 失败:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req), e);
            response.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("{}.login 出错:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req), e);
            //return ResultInfo.error("登录失败,请稍后重试 ");
            response.setResultInfo("100000001", null);
        }
        logger.info("/app login耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @AllowAccess
    @RequestMapping(name = "登出", value = "/loginOut", method = RequestMethod.POST)
    public ResultInfo loginOut(UsersRequest req, HttpServletRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        LoginUser loginUser = null;
        try {
            response.setData(true);
            logger.info("{}BB直播app：{},登录成功. result:{}", req.getEmail(), JSONObject.toJSONString(req), JSONObject.toJSONString(loginUser));
            LogUtils.logUserModifyOperates(this.getClass().getName() + ".login", req, getLoginUserAPP());
        } catch (BusinessException e) {
            logger.error("{}.login 失败:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req), e);
            response.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("{}.login 出错:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req), e);
            //return ResultInfo.error("登录失败,请稍后重试 ");
            response.setResultInfo("100000001", null);
        }
        logger.info("/app login耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


}
