package com.likes.modules.admin.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.mybatis.entity.SysTags;
import com.likes.common.util.LogUtils;
import com.likes.modules.admin.sys.service.SysTagsService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/tags")
public class SysTagsController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SysTagsService sysTagsService;

    @ResponseBody
    @RequestMapping(name = "标签保存,话题保存", value = "/saveSysTags", method = RequestMethod.POST)
    public ResultInfo saveSysTags(HttpServletRequest request, SysTags sysTags) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isEmpty(sysTags.getTagname()) || null == sysTags.getTagtype()) {
                return ResultInfo.error("名称或类型不能为空");
            }
            LoginUser loginAdmin = getLoginAdmin();
            response.setData(sysTagsService.saveSysTags(sysTags, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".saveSysTags", sysTags, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.saveSysTags,标签保存,话题保存失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysTags), e);
        } catch (Exception e) {
            logger.error("{}.saveSysTags,标签保存,话题保存失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysTags), e);
            response = ResultInfo.error("标签保存,话题保存失败");
        }
        logger.info("/saveSysTags耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "标签编辑,话题编辑", value = "/updateSysTags", method = RequestMethod.POST)
    public ResultInfo updateSysTags(HttpServletRequest request, SysTags sysTags) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == sysTags.getTagid() || StringUtils.isEmpty(sysTags.getTagname()) || null == sysTags.getTagtype()) {
                return ResultInfo.error("ID或名称或类型不能为空");
            }
            LoginUser loginAdmin = getLoginAdmin();
            response.setData(sysTagsService.updateSysTags(sysTags, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".updateSysTags", sysTags, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.updateSysTags,标签编辑,话题编辑失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysTags), e);
        } catch (Exception e) {
            logger.error("{}.updateSysTags,标签编辑,话题编辑失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysTags), e);
            response = ResultInfo.error("标签编辑,话题编辑失败");
        }
        logger.info("/updateSysTags耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "标签列表,话题管理", value = "/sysTagslList", method = RequestMethod.GET)
    public ResultInfo sysTagslList(HttpServletRequest request, PageBounds page, SysTags req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == req.getTagtype()) {
                return ResultInfo.error("类型不能为空");
            }
            response.setData(sysTagsService.sysTagslList(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.sysTagslList,标签列表,话题管理获取失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            logger.error("{}.sysTagslList,标签列表,话题管理获取失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
            response = ResultInfo.error("标签列表,话题管理获取失败");
        }
        logger.info("/sysTagslList{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "标签详细,话题详细", value = "/getSysTagsDetail", method = RequestMethod.GET)
    public ResultInfo getSysTagsDetail(HttpServletRequest request, SysTags req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == req.getTagid() || null == req.getTagtype()) {
                return ResultInfo.error("ID或类型不能为空");
            }
            response.setData(sysTagsService.getSysTagsDetail(req));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getSysTagsDetail,标签详细,话题详细获取失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            logger.error("{}.getSysTagsDetail,标签详细,话题详细获取失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
            response = ResultInfo.error("标签详细,话题详细获取失败");
        }
        logger.info("/getSysTagsDetail耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "标签删除,话题删除", value = "/delSysTags", method = RequestMethod.POST)
    public ResultInfo delSysTags(HttpServletRequest request, SysTags req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == req.getTagid()) {
                return ResultInfo.error("ID不能为空");
            }
            LoginUser loginAdmin = getLoginAdmin();
            response.setData(sysTagsService.delSysTags(req, loginAdmin));
            LogUtils.logUserModifyOperates(getClass().getName() + ".delSysTags", req, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.delSysTags,标签删除,话题删除失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
        } catch (Exception e) {
            logger.error("{}.delSysTags,标签删除,话题删除失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(req), e);
            response = ResultInfo.error("标签删除,话题删除失败");
        }
        logger.info("/delSysTags耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

}
