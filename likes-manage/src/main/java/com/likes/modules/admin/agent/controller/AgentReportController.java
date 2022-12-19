package com.likes.modules.admin.agent.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.constant.Constants;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.dto.AgentData;
import com.likes.common.model.dto.AgentLineData;
import com.likes.common.model.request.*;
import com.likes.common.model.response.TeamAdminResponse;
import com.likes.common.model.response.TeamResponse;
import com.likes.common.util.LogUtils;
import com.likes.modules.admin.agent.service.AgentReportService;
import com.likes.modules.admin.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author 阿布
 * <p>
 * 代理报表
 */
@Controller
@RequestMapping(value = "/agentreport")
public class AgentReportController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private AgentReportService agentReportService;
    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(name = "代理报表", value = "/getAgentList", method = RequestMethod.GET)
    public ResultInfo getAgentList(AgentAdminRequest req, PageBounds page) {
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.agentReportService.agentList(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getAgentList,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("代理报表出错");
            logger.error("{}.getAgentList,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "代理报表查看明细", value = "/getDetail", method = RequestMethod.GET)
    public ResultInfo getDetail(SysAgentinfoReq req, PageBounds page) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isEmpty(req.getAccno())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_21001.getCode(), "无效参数");
            }
            response.setData(this.agentReportService.getDetail(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getDetail,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("代理报表查看明细出错");
            logger.error("{}.getDetail,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "代理报表查看下级", value = "/getNextList", method = RequestMethod.GET)
    public ResultInfo getNextList(SysAgentinfoReq req, PageBounds page) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isEmpty(req.getAccno())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_21001.getCode(), "无效参数");
            }
            response.setData(this.agentReportService.getNextList(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getNextList,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("代理报表查看下级出错");
            logger.error("{}.getNextList,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "代理报表下级明细", value = "/getNextDetail", method = RequestMethod.GET)
    public ResultInfo getNextDetail(SysAgentinfoReq req, PageBounds page) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isEmpty(req.getAccno())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_21001.getCode(), "无效参数");
            }
            response.setData(this.agentReportService.getNextDetail(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getNextDetail,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("代理报表下级明细出错");
            logger.error("{}.getNextDetail,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "团队交易明细", value = "/teamTradingList", method = RequestMethod.GET)
    public ResultInfo agentDetailList(AgentDetailReq req, PageBounds page) {
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.agentReportService.teamTradingList(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getNextDetail,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("代理报表下级明细出错");
            logger.error("{}.getNextDetail,出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "代理报表查看个人提现", value = "/getUserWithdraw", method = RequestMethod.GET)
    public ResultInfo getUserWithdraw(SysAgentinfoReq req, PageBounds page) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isEmpty(req.getAccno())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_21001.getCode(), "无效参数");
            }
            response.setData(this.agentReportService.getUserWithdraw(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getUserWithdraw,失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("代理报表查看个人提现出错");
            logger.error("{}.getUserWithdraw,代理报表查看个人提现:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "代理定时任务测试，暂未对外", value = "/task", method = RequestMethod.GET)
    public ResultInfo doTask(@RequestParam("day") String day) {
        ResultInfo response = ResultInfo.ok();
        try {
            response = ResultInfo.ok(this.agentReportService.doDayExportNew(day, "(手动执行)"));
            LoginUser loginUser = getLoginAdmin();
            LogUtils.logUserModifyOperates(getClass().getName() + ".doTask", "执行代理任务", loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doTask,失败:{},params:{}", getClass().getName(), e.getMessage(), "执行代理任务", e);
        } catch (Exception e) {
            response = ResultInfo.error("执行代理任务出错");
            logger.error("{}.doTask,出错:{},params:{}", getClass().getName(), e.getMessage(), "执行代理任务", e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "团队报表", value = "/adminTeamList", method = RequestMethod.POST)
    public ResultInfo<TeamAdminResponse> adminTeamList(TeamRequest req, PageBounds page, HttpServletRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(agentReportService.teamReport(req, page));
        } catch (BusinessException e) {
            logger.error("{}.login 失败:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req.getAcclogin()), e);
            response.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("{}.login 出错:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req.getAcclogin()), e);
            //return ResultInfo.error("登录失败,请稍后重试 ");
            response.setResultInfo("100000001", null);
        }
        logger.info("/app login耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "数据报表", value = "/adminDataList", method = RequestMethod.POST)
    public ResultInfo<AgentData> adminDataList(AgentOrderReq req, PageBounds page, HttpServletRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(agentReportService.dataList(req, page));
        } catch (BusinessException e) {
            logger.error("{}.login 失败:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req.getAccno()), e);
            response.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("{}.login 出错:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req.getAccno()), e);
            //return ResultInfo.error("登录失败,请稍后重试 ");
            response.setResultInfo("100000001", null);
        }
        logger.info("/app login耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "每日报表", value = "/everyDayList", method = RequestMethod.POST)
    public ResultInfo<AgentData> everyDayList(AgentOrderReq req, PageBounds page, HttpServletRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(agentReportService.everyDayList(req, page));
        } catch (BusinessException e) {
            logger.error("{}.login 失败:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req.getAccno()), e);
            response.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("{}.login 出错:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req.getAccno()), e);
            //return ResultInfo.error("登录失败,请稍后重试 ");
            response.setResultInfo("100000001", null);
        }
        logger.info("/app login耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "代理线报表", value = "/agentLineList", method = RequestMethod.POST)
    public ResultInfo<AgentLineData> agentLineList(AgentLineReq req, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(agentReportService.agentLineList(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            response.setResultInfo("100000001", null);
        }
        logger.info("/app login耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }




}
