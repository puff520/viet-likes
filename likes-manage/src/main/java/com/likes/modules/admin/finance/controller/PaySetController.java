package com.likes.modules.admin.finance.controller;

import com.likes.common.BaseController;
import com.likes.common.annotation.Syslog;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.PaySetRequest;
import com.likes.common.util.LogUtils;
import com.likes.modules.admin.finance.service.PaySetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 支付设定
 *
 * @author bjkj
 */
@Controller
@RequestMapping(value = "/payset")
public class PaySetController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private PaySetService paySetService;

    @Syslog("支付设定")
    @ResponseBody
    @RequestMapping(name = "支付设定", value = "/list", method = RequestMethod.GET)
    public ResultInfo list(PaySetRequest paySetRequest, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.paySetService.getList2(paySetRequest, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.list支付设定出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("支付设定失败");
            logger.error("{}.list支付设定出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/list耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("支付设定新增")
    @ResponseBody
    @RequestMapping(name = "支付设定新增", value = "/doSave", method = RequestMethod.POST)
    public ResultInfo doSave(PaySetRequest sysPayset) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.paySetService.doSave(sysPayset, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doSave", sysPayset, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doSave支付设定新增出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("支付设定新增失败");
            logger.error("{}.doSave支付设定新增出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/doSave耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("支付设定启用禁用")
    @ResponseBody
    @RequestMapping(name = "支付设定启用禁用", value = "/doUpdateStatus", method = RequestMethod.POST)
    public ResultInfo doUpdateStatus(PaySetRequest sysPayset) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.paySetService.doUpdateStatus2(sysPayset, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doUpdateStatus", sysPayset, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doUpdateStatus支付设定启用禁用出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("支付设定启用禁用失败");
            logger.error("{}.doUpdateStatus支付设定启用禁用出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/doUpdateStatus耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @Syslog("支付设定删除")
    @ResponseBody
    @RequestMapping(name = "支付设定删除", value = "/doDel", method = RequestMethod.POST)
    public ResultInfo doDel(PaySetRequest sysPayset) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.paySetService.doDel2(sysPayset, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doDel", sysPayset, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doDel支付设定删除出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("支付设定删除失败");
            logger.error("{}.doDel支付设定删除出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/doDel耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

}
