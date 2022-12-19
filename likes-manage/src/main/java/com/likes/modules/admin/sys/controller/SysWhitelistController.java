package com.likes.modules.admin.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.annotation.Syslog;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.mybatis.entity.SysWhitelist;
import com.likes.common.service.sys.SysWhitelistService;
import com.likes.common.util.LogUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author mang guo
 * @version 1.0
 * @Description
 * @revise
 * @time 2020年3月17日
 * @copyright Copyright @2017, Co., Ltd. All right.
 */

@Controller
@RequestMapping(value = "/white")
public class SysWhitelistController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SysWhitelistService sysWhitelistService;

    @Syslog("白名单新增")
    @ResponseBody
    @RequestMapping(name = "白名单新增", value = "/doSave", method = RequestMethod.POST)
    public ResultInfo dosave(SysWhitelist sysWhitelist) {
        long start = System.currentTimeMillis();
        ResultInfo respone = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            if (StringUtils.isEmpty(sysWhitelist.getIpaddress())) {
                return ResultInfo.error("ip不能为空");
            }
            respone.setData(this.sysWhitelistService.doSave(sysWhitelist, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".dosave", sysWhitelist, loginUser);
        } catch (BusinessException e) {
            respone.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.dosave,白名单新增失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysWhitelist), e);
        } catch (Exception e) {
            logger.error("{}.dosave,白名单新增失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysWhitelist), e);
            respone = ResultInfo.error("白名单新增失败");
        }
        logger.info("/doSave耗时{}毫秒", (System.currentTimeMillis() - start));
        return respone;
    }


    @ResponseBody
    @RequestMapping(name = "IP白名单设置", value = "/list", method = RequestMethod.GET)
    public ResultInfo list(SysWhitelist sysWhite, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.sysWhitelistService.getList(sysWhite, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.dosave,IP白名单设置列表获取失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysWhite), e);
        } catch (Exception e) {
            logger.error("{}.dosave,IP白名单设置列表获取失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysWhite), e);
            response = ResultInfo.error("IP白名单设置列表获取失败");
        }
        logger.info("/list耗时{}毫秒", (System.currentTimeMillis() - start));

        return response;
    }

    @Syslog("白名单编辑")
    @ResponseBody
    @RequestMapping(name = "白名单编辑", value = "/doUpdate", method = RequestMethod.POST)
    public ResultInfo doUpdate(SysWhitelist sysWhitelist) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == sysWhitelist.getWhiteid()) {
                return ResultInfo.error("id不能为空");
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysWhitelistService.doUpdate(sysWhitelist, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doUpdate", sysWhitelist, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doUpdate,白名单编辑失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysWhitelist), e);
        } catch (Exception e) {
            logger.error("{}.doUpdate,白名单编辑失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysWhitelist), e);
            response = ResultInfo.error("白名单编辑失败");
        }
        logger.info("/doUpdate耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("白名单删除")
    @ResponseBody
    @RequestMapping(name = "白名单删除", value = "/doDelete", method = RequestMethod.POST)
    public ResultInfo doDelWhite(SysWhitelist sysWhitelist) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == sysWhitelist.getWhiteid()) {
                return ResultInfo.error("参数ID不能为空");
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysWhitelistService.doDelWhite(sysWhitelist, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + "doUpdate", sysWhitelist, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doDelWhite,白名单删除失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysWhitelist), e);
        } catch (Exception e) {
            logger.error("{}.doDelWhite,白名单删除失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysWhitelist), e);
            response = ResultInfo.error("白名单删除失败");
        }
        logger.info("/doUpdate耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("白名单批量删除")
    @ResponseBody
    @RequestMapping(name = "白名单删除", value = "/doDeletes", method = RequestMethod.POST)
    public ResultInfo doDelWhites(String ids){
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            for (String id : ids.split(",")){
                SysWhitelist sysWhitelist = new SysWhitelist();
                sysWhitelist.setWhiteid(Long.parseLong(id));
                this.sysWhitelistService.doDelWhite(sysWhitelist, loginUser);
            }
            LogUtils.logUserModifyOperates(getClass().getName() + "doUpdate", ids, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doDelWhite,白名单删除失败:{},params:{}", getClass().getName(), e.getMessage(), ids, e);
        } catch (Exception e) {
            logger.error("{}.doDelWhite,白名单删除失败:{},params:{}", getClass().getName(), e.getMessage(), ids, e);
            response = ResultInfo.error("白名单删除失败");
        }
        logger.info("/doUpdate耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }
}
