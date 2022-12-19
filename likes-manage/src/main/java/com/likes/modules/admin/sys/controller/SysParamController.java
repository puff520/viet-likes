package com.likes.modules.admin.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.mybatis.entity.SysParameter;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.LogUtils;
import com.likes.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author abu
 * @version 1.0
 * @Description 系统参数
 * @revise
 * @time 2018年7月6日 下午2:06:33
 */
@Controller
@RequestMapping("/sysparam")
public class SysParamController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SysParamService sysParamService;

    @ResponseBody
    @RequestMapping(name = "系统参数", value = "/list", method = RequestMethod.GET)
    public ResultInfo list(SysParameter req, PageBounds page) {
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.sysParamService.getList(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.list 系统参数获取失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e){
            logger.error("{}.list 系统参数获取失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
            response = ResultInfo.error("系统参数获取失败");
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "系统参数根据id详情", value = "/getDetail", method = RequestMethod.GET)
    public ResultInfo getDetails(Long sysparamid) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (sysparamid == null) {
                return ResultInfo.error("参数错误,ID不能为空");
            }
            response.setData(this.sysParamService.getDetails(sysparamid));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getDetails 系统参数根据id详情失败:{},params:{}", getClass().getName(), e.getMessage(), sysparamid, e);
        } catch (Exception e) {
            logger.error("{}.getDetails 系统参数根据id详情失败:{},params:{}", getClass().getName(), e.getMessage(), sysparamid, e);
            response = ResultInfo.error("系统参数根据id详情失败");
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "系统参数根据code获取详情", value = "/getByCode", method = RequestMethod.GET)
    public ResultInfo getByCode(String sysparamcode) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isEmpty("sysparamcode")) {
                return ResultInfo.error("参数不能为空");
            }
            response.setData(this.sysParamService.getByCode(sysparamcode));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getByCode 系统参数根据code获取详情失败:{},params:{}", getClass().getName(), e.getMessage(), sysparamcode, e);
        } catch (Exception e) {
            logger.error("{}.getByCode 系统参数根据code获取详情失败:{},params:{}", getClass().getName(), e.getMessage(), sysparamcode, e);
            response = ResultInfo.error("系统参数根据code获取详情失败");
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "系统参数保存", value = "/save", method = RequestMethod.POST)
    public ResultInfo save(SysParameter req) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isEmpty(req.getSysparamname()) || StringUtils.isEmpty(req.getSysparamcode()) || StringUtils.isEmpty(req.getSysparamvalue())) {
                return ResultInfo.error("名称或代码或值不能为空");
            }
            LoginUser loginUser = getLoginAdmin();
            this.sysParamService.save(req, loginUser);
            LogUtils.logUserModifyOperates(getClass().getName() + ".save", req, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.save 系统参数保存失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            logger.error("{}.save 系统参数保存失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
            response = ResultInfo.error("系统参数保存失败");
        }

        return response;
    }

    @ResponseBody
    @RequestMapping(name = "系统参数编辑,系统参数禁用启用", value = "/update", method = RequestMethod.POST)
    public ResultInfo update(SysParameter req) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (req.getSysparamid() == null) {
                return ResultInfo.error("ID不能为空");
            }
            LoginUser loginUser = getLoginAdmin();
            this.sysParamService.edit(req, loginUser);
            LogUtils.logUserModifyOperates(getClass().getName() + ".update", req, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.update 系统参数编辑,系统参数禁用启用失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            logger.error("{}.update 系统参数编辑,系统参数禁用启用失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
            response = ResultInfo.error("系统参数编辑,系统参数禁用启用失败");
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "系统参数删除", value = "/delete", method = RequestMethod.POST)
    public ResultInfo delete(Long sysparamid) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == sysparamid) {
                return ResultInfo.error("ID不能为空");
            }
            LoginUser loginUser = getLoginAdmin();
            this.sysParamService.doDel(sysparamid, loginUser);
            LogUtils.logUserModifyOperates(getClass().getName() + ".delete", sysparamid, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.delete 系统参数删除失败:{},params:{}", getClass().getName(), e.getMessage(), sysparamid, e);
        } catch (Exception e) {
            logger.error("{}.delete 系统参数删除失败:{},params:{}", getClass().getName(), e.getMessage(), sysparamid, e);
            response = ResultInfo.error("系统参数删除失败");
        }
        return response;
    }
}