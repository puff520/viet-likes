package com.likes.modules.admin.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.annotation.Syslog;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.SysDomainRequest;
import com.likes.common.service.sys.SysDomainService;
import com.likes.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/domain")
public class SysDomainController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SysDomainService sysDomainService;


    @ResponseBody
    @RequestMapping(name = "域名列表", value = "/list", method = RequestMethod.GET)
    public ResultInfo list(SysDomainRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.sysDomainService.domainList(request));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            response = ResultInfo.error("获取域名列表失败");
        }
        return response;
    }

    @Syslog("域名新增")
    @ResponseBody
    @RequestMapping(name = "域名新增", value = "/doSave", method = RequestMethod.POST)
    public ResultInfo doSave(@RequestBody SysDomainRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
//            if (StringUtils.isEmpty(sysCdn.getDomain()) || StringUtils.isEmpty(sysCdn.getSecretkey()) || StringUtils.isEmpty(sysCdn.getCname())) {
//                return ResultInfo.error("原始域名,密匙,cdn域名,不能为空");
//            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysDomainService.add(request, loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            response = ResultInfo.error("域名新增失败");
        }
        return response;
    }


    @Syslog("域名编辑")
    @ResponseBody
    @RequestMapping(name = "域名编辑", value = "/edit", method = RequestMethod.POST)
    public ResultInfo doUpdate(@RequestBody  SysDomainRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
//            if (null == sysCdn.getCdnid() || StringUtils.isEmpty(sysCdn.getDomain()) || StringUtils.isEmpty(sysCdn.getSecretkey()) || StringUtils.isEmpty(sysCdn.getCname())) {
//                return ResultInfo.error("ID，原始域名,密匙,cdn域名,不能为空");
//            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysDomainService.update(request, loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            response = ResultInfo.error("域名编辑失败");
        }
        return response;
    }


    @Syslog("域名删除")
    @ResponseBody
    @RequestMapping(name = "域名删除", value = "/del", method = RequestMethod.GET)
    public ResultInfo doDelCdn(String ids) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isBlank(ids)) {
                return ResultInfo.error("ID不能为空");
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysDomainService.delete(ids, loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            response = ResultInfo.error("域名删除失败");
        }
        logger.info("/doDelCdn耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }



}
