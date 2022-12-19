package com.likes.modules.admin.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.annotation.Syslog;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.mybatis.entity.InfSysnotice;
import com.likes.common.service.sys.InfSysnoticeService;
import com.likes.common.util.LogUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/notice")
public class InfSysnoticeController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private InfSysnoticeService infSysnoticeService;

    @ResponseBody
    @RequestMapping(name = "公告管理", value = "/list", method = RequestMethod.GET)
    public ResultInfo list(InfSysnotice infSysnotice, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.infSysnoticeService.getList(infSysnotice, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.list 公告管理失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(infSysnotice), e);
        } catch (Exception e) {
            logger.error("{}.list 公告管理失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(infSysnotice), e);
            response = ResultInfo.error("获取公告管理失败");
        }
        logger.info("/list耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("公告新增")
    @ResponseBody
    @RequestMapping(name = "公告新增", value = "/doSave", method = RequestMethod.POST)
    public ResultInfo doSave(InfSysnotice infSysnotice) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == infSysnotice.getType()) {
                return ResultInfo.error("公告类型或公告参数不能为空");
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.infSysnoticeService.doSave(infSysnotice, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doSave", infSysnotice, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doSave 公告新增失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(infSysnotice), e);
        } catch (Exception e) {
            response = ResultInfo.error("公告新增失败");
            logger.error("{}.doSave 公告新增出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(infSysnotice), e);
        }
        logger.info("/doSave耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("公告编辑")
    @ResponseBody
    @RequestMapping(name = "公告编辑", value = "/doUpdate", method = RequestMethod.POST)
    public ResultInfo doUpdate(InfSysnotice infSysnotice) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == infSysnotice.getNoticeid() || null == infSysnotice.getType()) {
                return ResultInfo.error("公告ID,公告类型,不能为空");
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.infSysnoticeService.doUpdate(infSysnotice, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doUpdate", infSysnotice, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doUpdate 公告编辑失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(infSysnotice), e);
        } catch (Exception e) {
            response = ResultInfo.error("公告编辑失败");
            logger.error("{}.doUpdate 公告编辑出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(infSysnotice), e);
        }
        logger.info("/doUpdate耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("公告删除")
    @ResponseBody
    @RequestMapping(name = "公告删除", value = "/doDelNotice", method = RequestMethod.POST)
    public ResultInfo doDel(InfSysnotice infSysnotice) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == infSysnotice.getNoticeid()) {
                return ResultInfo.error("公告ID为空");
            }
            LoginUser loginUser = getLoginAdmin();
            infSysnotice.setIsDelete(true);
            response.setData(this.infSysnoticeService.doDel(infSysnotice, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doDel", infSysnotice, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doDel 出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(infSysnotice), e);
        } catch (Exception e) {
            response = ResultInfo.error("公告删除失败");
            logger.error("{}.doDel 出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(infSysnotice), e);
        }
        logger.info("/doDel耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("启停")
    @ResponseBody
    @RequestMapping(name = "启停", value = "/doWorkStatus", method = RequestMethod.POST)
    public ResultInfo doWorkStatus(InfSysnotice infSysnotice) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        if (infSysnotice.getNoticeid() == null) {
            return ResultInfo.error("公告ID为空");
        }
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.infSysnoticeService.doWorkStatus(infSysnotice));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doWorkStatus", infSysnotice, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doWorkStatus 出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(infSysnotice), e);
        } catch (Exception e) {
            response.toError("公告启停失败");
            logger.error("{}.doWorkStatus 出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(infSysnotice), e);
        }
        return response;
    }


}
