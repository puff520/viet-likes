package com.likes.modules.admin.users.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.EntryOrderReq;
import com.likes.common.model.request.MemGoldChangeRequest;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.service.member.MemberOtherService;
import com.likes.modules.admin.users.service.MoneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 钱包
 *
 * @author bjkj
 */
@Controller
@RequestMapping(value = "/money")
public class MoneyController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private MoneyService moneyService;
    @Autowired
    private MemberOtherService memberother;

    @ResponseBody
    @RequestMapping(name = "我的等级", value = "/myLevel", method = RequestMethod.GET)
    public ResultInfo myLevel() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(moneyService.myLevel(loginUserAPP.getAccno()));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.myLevel 失败:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("获取我的等级出错");
            logger.error("{}.myLevel 出错:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/myLevel耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "钱包地址", value = "/moneyAddress", method = RequestMethod.GET)
    public ResultInfo moneyAddress() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(moneyService.moneyAddress(loginUserAPP.getAccno()));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.moneyAddress 失败:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("获取钱包地址出错");
            logger.error("{}.moneyAddress 出错:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/moneyAddress耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "我的页面统计", value = "/myPageCount", method = RequestMethod.GET)
    public ResultInfo myPageCount(UsersRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(moneyService.myPageCount(loginUserAPP.getAccno()));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.myIncomeAndExpensesList 失败:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("获取记录失败");
            logger.error("{}.myIncomeAndExpensesList 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/myIncomeAndExpensesList耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "我的流水", value = "/getAccountRecord", method = RequestMethod.GET)
    public ResultInfo getAccountRecord(MemGoldChangeRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            req.setAccno(loginUserAPP.getAccno());
            response = ResultInfo.ok(memberother.getMemberGoldChange(req));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getAccountRecord分页查询失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("分页查询出错");
            logger.error("{}.getAccountRecord分页查询出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getAccountRecord耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "我的收入/支出记录", value = "/myIncomeAndExpensesList", method = RequestMethod.GET)
    public ResultInfo myIncomeAndExpensesList(UsersRequest req, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            req.setAccno(loginUserAPP.getAccno());
            response.setData(moneyService.myIncomeAndExpensesList(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.myIncomeAndExpensesList 失败:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            response = ResultInfo.error("获取记录失败");
            logger.error("{}.myIncomeAndExpensesList 出错:{}, params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        }
        logger.info("/myIncomeAndExpensesList耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


}
