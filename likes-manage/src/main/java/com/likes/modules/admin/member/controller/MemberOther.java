package com.likes.modules.admin.member.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.likes.common.BaseController;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.annotation.Syslog;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.dto.member.MemGoldChangeTypeDO;
import com.likes.common.model.dto.member.MemberBankDTO;
import com.likes.common.model.request.*;
import com.likes.common.service.member.MemberOtherService;
import com.likes.common.util.LogUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.AbstractList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(value = "统计报表")
@RequestMapping(value = "/memberother")
public class MemberOther extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MemberOtherService memberother;



    @Syslog("删除会员银行卡")
    @ResponseBody
    @RequestMapping(name = "删除会员银行卡", value = "/deletebank", method = RequestMethod.POST)
    public ResultInfo delete(@RequestParam(name = "bankaccid", required = true) Long id) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(memberother.deleteForManager(id, loginUser.getAccno()));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.delete删除会员银行卡,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("删除会员银行卡四百");
            logger.error("{}.delete删除会员银行卡出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/delete耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "分页查询会员报表", value = "/memberreport", method = RequestMethod.GET)
    public ResultInfo report(MemberReportRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            response = ResultInfo.ok(memberother.getReport(req));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.list分页查询会员报表失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("分页查询会员报表出错");
            logger.error("{}.list分页查询会员报表出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/list耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("后台会员注册")
    @ResponseBody
    @RequestMapping(name = "注册", value = "/register", method = RequestMethod.POST)
    public ResultInfo register(UsersRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            req.setServerIp(getServerIp());
            req.setClintipadd(resolveIp(request));
            req.setClientPhoneModel(resolveClientPhoneModel(request));
            response.setData(memberother.register(req));
            LogUtils.logUserModifyOperates(this.getClass().getName() + ".register", req, getLoginUserAPP());
        } catch (BusinessException e) {
            logger.error("{}.register 失败:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req), e);
            response.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("{}.register 出错:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req), e);
            return ResultInfo.error("注册失败,请稍后重试 ");
        }
        logger.info("/app register耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "会员资金变动类型", value = "/memgoldchangtype", method = RequestMethod.GET)
    public ResultInfo getMemberGoldCahngeType() {
        ResultInfo response;
        MemGoldChangeTypeDO[] result = new MemGoldChangeTypeDO[21];
        result[0] = new MemGoldChangeTypeDO("1", "充值");
        result[1] = new MemGoldChangeTypeDO("7", "邀请用户");
        result[2] = new MemGoldChangeTypeDO("8", "充值附赠");
        result[3] = new MemGoldChangeTypeDO("10", "提现申请");
        result[4] = new MemGoldChangeTypeDO("11", "提现取消");
        result[5] = new MemGoldChangeTypeDO("12", "已提现");
        result[6] = new MemGoldChangeTypeDO("54", "提现失败");
        result[7] = new MemGoldChangeTypeDO("55", "手动入款");
        result[8] = new MemGoldChangeTypeDO("56", "手动出款");
        result[9] = new MemGoldChangeTypeDO("70", "彩金");
        result[10] = new MemGoldChangeTypeDO("71", "新人奖励");
        result[11] = new MemGoldChangeTypeDO("100", "购买VIP");
        result[12] = new MemGoldChangeTypeDO("101", "第一级返佣");
        result[13] = new MemGoldChangeTypeDO("102", "第二级返佣");
        result[14] = new MemGoldChangeTypeDO("103", "第三级级返佣");
        result[15] = new MemGoldChangeTypeDO("200", "任务奖励");
        result[16] = new MemGoldChangeTypeDO("202", "会员发布任务扣款");
        result[17] = new MemGoldChangeTypeDO("203", "会员发布任务抽水");
        result[18] = new MemGoldChangeTypeDO("301", "购买VIP第一级返佣");
        result[19] = new MemGoldChangeTypeDO("302", "购买VIP第二级返佣");
        result[20] = new MemGoldChangeTypeDO("303", "购买VIP第三级级返佣");

        response = ResultInfo.ok(result);
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "会员资金变动流程", value = "/memgoldchangelst", method = RequestMethod.GET)
    public ResultInfo getMemberGoldChange(MemGoldChangeRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            response = ResultInfo.ok(memberother.getMemberGoldChange(req));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.memgoldchangelst分页查询失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("分页查询出错");
            logger.error("{}.memgoldchangelst分页查询出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/memgoldchangelst耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @AllowAccess
    @ResponseBody
    @RequestMapping(name = "全局统计-会员", value = "/membercount", method = RequestMethod.GET)
    public ResultInfo getMemberCount() {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            response = ResultInfo.ok(memberother.getMemberCount());
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.membercount,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("分页查询出错");
            logger.error("{}.membercount,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/membercount耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @ApiOperation(value = "每日报表", notes = "每日报表接口")
    @RequestMapping(name = "每日报表", value = "/getDayReport", method = RequestMethod.GET)
    public ResultInfo getDayReport(DayReportRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            response = ResultInfo.ok(memberother.getDayReport(req));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getDayReport,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("分页查询出错");
            logger.error("{}.getDayReport,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getDayReport{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


    @AllowAccess
    @ResponseBody
    @RequestMapping(name = "vip变更记录", value = "/vipRecordList", method = RequestMethod.GET)
    public ResultInfo vipRecordList(VipRecordRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            response = ResultInfo.ok(memberother.vipRecordList(req));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getDayReport,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("分页查询出错");
            logger.error("{}.getDayReport,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getDayReport{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

}
