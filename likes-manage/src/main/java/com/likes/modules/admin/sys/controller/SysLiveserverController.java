package com.likes.modules.admin.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.mybatis.entity.SysLiveserver;
import com.likes.common.service.sys.SysLiveserverService;
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
@RequestMapping(value = "/liveserver")
public class SysLiveserverController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SysLiveserverService sysLiveserverService;

    @ResponseBody
    @RequestMapping(name = "直播服务器", value = "/list", method = RequestMethod.GET)
    public ResultInfo list(SysLiveserver sysLiveserver, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.sysLiveserverService.getList(sysLiveserver, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.list，获取直播服务器失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysLiveserver), e);
        } catch (Exception e) {
            logger.error("{}.list，获取直播服务器失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysLiveserver), e);
            response = ResultInfo.error("获取直播服务器失败");
        }
        logger.info("/list耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "直播服务器新增", value = "/doSave", method = RequestMethod.POST)
    public ResultInfo doSave(SysLiveserver sysLiveserver) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isEmpty(sysLiveserver.getServername()) || StringUtils.isEmpty(sysLiveserver.getServerurl()) || StringUtils.isEmpty(sysLiveserver.getRegion())) {
                return ResultInfo.error("服务器名称或服务器地址或服务器区域不能为空");
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysLiveserverService.doSave(sysLiveserver, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + "doSave", sysLiveserver, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doSave,直播服务器新增失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysLiveserver), e);
        } catch (Exception e) {
            logger.error("{}.doSave,直播服务器新增失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysLiveserver), e);
            response = ResultInfo.error("直播服务器新增失败");
        }
        logger.info("/doSave耗时{}毫秒", (System.currentTimeMillis() - start));

        return response;
    }

    @ResponseBody
    @RequestMapping(name = "直播服务器编辑", value = "/doUpdate", method = RequestMethod.POST)
    public ResultInfo doUpdate(SysLiveserver sysLiveserver) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == sysLiveserver.getLiveid() || StringUtils.isEmpty(sysLiveserver.getServername()) || StringUtils.isEmpty(sysLiveserver.getServerurl()) || StringUtils.isEmpty(sysLiveserver.getRegion())) {
                return ResultInfo.error("ID或服务器名称或服务器地址或服务器区域不能为空");
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysLiveserverService.doUpdate(sysLiveserver, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doUpdate", sysLiveserver, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doUpdate,直播服务器编辑失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysLiveserver), e);
        } catch (Exception e) {
            logger.error("{}.doUpdate,直播服务器编辑失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysLiveserver), e);
            response = ResultInfo.error("直播服务器编辑失败");
        }
        logger.info("/doUpdate耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "直播服务器启用禁用", value = "/doUpdateStatusLiveserver", method = RequestMethod.POST)
    public ResultInfo doDelLiveserver(SysLiveserver sysLiveserver) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == sysLiveserver.getLiveid()) {
                return ResultInfo.error("ID不能为空");
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysLiveserverService.doDelLiveserver(sysLiveserver, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doDelLiveserver", sysLiveserver, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doDelLiveserver,直播服务器启用禁用失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysLiveserver), e);
        } catch (Exception e) {
            logger.error("{}.doDelLiveserver,直播服务器启用禁用失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysLiveserver), e);
            response = ResultInfo.error("直播服务器启用禁用失败");
        }
        logger.info("/doDel直播服务器耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "直播服务器删除", value = "/doDelete", method = RequestMethod.POST)
    public ResultInfo doDelete(SysLiveserver sysLiveserver) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == sysLiveserver.getLiveid()) {
                return ResultInfo.error("ID不能为空");
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysLiveserverService.doDelete(sysLiveserver, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doDelete", sysLiveserver, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doDelete,直播服务器删除失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysLiveserver), e);
        } catch (Exception e) {
            logger.error("{}.doDelete,直播服务器删除失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysLiveserver), e);
            response = ResultInfo.error("直播服务器删除失败");
        }
        logger.info("/doDelete耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


}
