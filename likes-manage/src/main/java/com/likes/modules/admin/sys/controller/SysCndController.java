package com.likes.modules.admin.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.mybatis.entity.SysCdn;
import com.likes.common.service.sys.SysCndService;
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
@RequestMapping(value = "/cdn")
public class SysCndController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SysCndService sysCndService;

    @AllowAccess
    @ResponseBody
    @RequestMapping(name = "刷新CDN", value = "/refresh")
    public ResultInfo refresh() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.sysCndService.refresh());
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.refresh 获取参数失败:{},params:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            logger.error("{}.refresh 获取参数失败:{},params:{}", getClass().getName(), e.getMessage(), e);
            response = ResultInfo.error("获取参数失败");
        }
        logger.info("/refresh耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "CDN管理", value = "/list", method = RequestMethod.GET)
    public ResultInfo list(SysCdn sysCdn, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.sysCndService.getList(sysCdn, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.refresh 获取CDN管理失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysCdn), e);
        } catch (Exception e) {
            logger.error("{}.refresh 获取CDN管理失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysCdn), e);
            response = ResultInfo.error("获取CDN管理失败");
        }
        logger.info("/list耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "CDN新增", value = "/doSave", method = RequestMethod.POST)
    public ResultInfo doSave(SysCdn sysCdn) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isEmpty(sysCdn.getDomain()) || StringUtils.isEmpty(sysCdn.getSecretkey()) || StringUtils.isEmpty(sysCdn.getCname())) {
                return ResultInfo.error("原始域名,密匙,cdn域名,不能为空");
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysCndService.doSave(sysCdn, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doSave", sysCdn, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doSave CDN新增失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysCdn), e);
        } catch (Exception e) {
            logger.error("{}.doSave CDN新增失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysCdn), e);
            response = ResultInfo.error("CDN新增失败");
        }
        logger.info("/doSave耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "CDN编辑", value = "/doUpdate", method = RequestMethod.POST)
    public ResultInfo doUpdate(SysCdn sysCdn) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == sysCdn.getCdnid() || StringUtils.isEmpty(sysCdn.getDomain()) || StringUtils.isEmpty(sysCdn.getSecretkey()) || StringUtils.isEmpty(sysCdn.getCname())) {
                return ResultInfo.error("ID，原始域名,密匙,cdn域名,不能为空");
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysCndService.doUpdate(sysCdn, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doUpdate", sysCdn, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doUpdate CDN编辑失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysCdn), e);
        } catch (Exception e) {
            logger.error("{}.doUpdate CDN编辑失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysCdn), e);
            response = ResultInfo.error("CDN编辑失败");
        }
        logger.info("/doUpdate耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "CDN删除", value = "/doDelCdn", method = RequestMethod.POST)
    public ResultInfo doDelCdn(SysCdn sysCdn) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == sysCdn.getCdnid()) {
                return ResultInfo.error("ID不能为空");
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysCndService.doDelCdn(sysCdn, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doDelCdn", sysCdn, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doDelCdn CDN删除失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysCdn), e);
        } catch (Exception e) {
            logger.error("{}.doDelCdn CDN删除失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysCdn), e);
            response = ResultInfo.error("CDN删除失败");
        }
        logger.info("/doDelCdn耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "CDN启用禁用", value = "/doUpdateStatus", method = RequestMethod.POST)
    public ResultInfo doUpdateStatus(SysCdn sysCdn) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == sysCdn.getCdnid() || null == sysCdn.getStatus()) {
                return ResultInfo.error("ID，或状态，不能为空");
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysCndService.doUpdateStatus(sysCdn, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doUpdateStatus", sysCdn, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doUpdateStatus CDN启用禁用失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysCdn), e);
        } catch (Exception e) {
            logger.error("{}.doUpdateStatus CDN启用禁用失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysCdn), e);
            response = ResultInfo.error("CDN启用禁用失败");
        }
        logger.info("/doUpdateStatus耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

}
