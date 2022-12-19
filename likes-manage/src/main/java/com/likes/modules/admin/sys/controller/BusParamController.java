package com.likes.modules.admin.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.annotation.Syslog;
import com.likes.common.constant.Constants;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.SysBusParamLst;
import com.likes.common.mybatis.entity.SysBusparameter;
import com.likes.common.service.sys.SysBusParamService;
import com.likes.common.util.LogUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务参数
 *
 * @author abu
 */

@Controller
@RequestMapping("/busparam")
public class BusParamController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SysBusParamService sysBusParamService;

    @ResponseBody
    @RequestMapping(name = "业务参数", value = "/list", method = RequestMethod.GET)
    public ResultInfo list(SysBusparameter sysBusparameter, PageBounds page) {
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.sysBusParamService.getList(sysBusparameter, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.list 获取参数失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysBusparameter), e);
        } catch (Exception e) {
            logger.error("{}.list 获取参数失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysBusparameter), e);
            response = ResultInfo.error("获取参数失败");
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "业务参数列表树(包含子孙)", value = "/listsub", method = RequestMethod.GET)
    public ResultInfo listAll(SysBusparameter sysBusparameter) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isEmpty(sysBusparameter.getBusparamcode())) {
                return ResultInfo.error("参数不能为空");
            }
            response.setData(this.sysBusParamService.listAll(sysBusparameter));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.listAll 获取参数失败:{},params:{}", getClass().getName(), e.getMessage(), sysBusparameter.getBusparamcode(), e);
        } catch (Exception e) {
            logger.error("{}.listAll 获取参数失败:{},params:{}", getClass().getName(), e.getMessage(), sysBusparameter.getBusparamcode(), e);
            response = ResultInfo.error("获取业务参数列表树(包含子孙)失败");
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "业务参数子节点列表", value = "/getChild", method = RequestMethod.GET)
    public ResultInfo getChild(String busparamcode) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isEmpty(busparamcode)) {
                return ResultInfo.error("参数不能为空");
            }
            response.setData(this.sysBusParamService.selectByParedubpcode(busparamcode));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getChild 业务参数子节点列表失败:{},params:{}", getClass().getName(), e.getMessage(), busparamcode, e);
        } catch (Exception e) {
            logger.error("{}.getChild 业务参数子节点列表失败:{},params:{}", getClass().getName(), e.getMessage(), busparamcode, e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "业务参数子节点列表", value = "/getDianZaiConfig", method = RequestMethod.GET)
    public ResultInfo getConfig() {
        ResultInfo response = ResultInfo.ok();
        try {
            List<SysBusparameter> lst = this.sysBusParamService.selectByParedubpcode("dianzai");
            Map<String,String> result = new HashMap(){};
            for(SysBusparameter sysBusparameter:lst){
                result.put(sysBusparameter.getBusparamcode(),sysBusparameter.getBusparamname());
            }
            response.setData(result);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getChild 业务参数子节点列表失败:{},params:{}", getClass().getName(), e.getMessage(), "点赞", e);
        } catch (Exception e) {
            logger.error("{}.getChild 业务参数子节点列表失败:{},params:{}", getClass().getName(), e.getMessage(), "点赞", e);
        }
        return response;
    }

    @Syslog("基本配置")
    @ResponseBody
    @RequestMapping(name="业务参数集合保存",value = "/saveLst",method = RequestMethod.POST)
    public ResultInfo saveLst(@RequestBody SysBusParamLst sysBusParamLst){
        ResultInfo response = ResultInfo.ok();
        try {

            LoginUser loginAdmin = getLoginAdmin();
            for (Map.Entry<String, String> entry : sysBusParamLst.getConfigs().entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if(value == null)
                    value = "";
//                logger.info("key:{},value:{}",key,value);
                this.sysBusParamService.doSaveParam("dianzai",key,value,loginAdmin);
            }

            // sysBusParamLst.getConfigs().forEach((key,value)->
            //        this.sysBusParamService.doSaveParam("dianzai",key,value.toString(),loginAdmin));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
//            logger.error("{}.save 失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(map), e);
        } catch (Exception e) {
            response = ResultInfo.error("业务参数保存失败");
            logger.error("{}.save 出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysBusParamLst), e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "业务参数保存", value = "/save", method = RequestMethod.POST)
    public ResultInfo save(SysBusparameter sysBusparameter) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isEmpty(sysBusparameter.getBusparamcode()) || StringUtils.isEmpty(sysBusparameter.getBusparamname())) {
                return ResultInfo.error("业务参数不能为空");
            }
            LoginUser loginAdmin = getLoginAdmin();
            this.sysBusParamService.doSaveParam(sysBusparameter, loginAdmin);
            LogUtils.logUserModifyOperates(getClass().getName() + ".save", sysBusparameter, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.save 失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysBusparameter), e);
        } catch (Exception e) {
            response = ResultInfo.error("业务参数保存失败");
            logger.error("{}.save 出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysBusparameter), e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "业务参数编辑,业务参数禁用启用", value = "/update", method = RequestMethod.POST)
    public ResultInfo update(SysBusparameter sysBusparameter) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == sysBusparameter.getBusparamid() || sysBusparameter.getBusparamid().equals(Constants.DEFAULT_LONG)) {
                return ResultInfo.error("参数错误");
            }
            LoginUser loginAdmin = getLoginAdmin();
            this.sysBusParamService.doUpdateParam(sysBusparameter, loginAdmin);
            LogUtils.logUserModifyOperates(getClass().getName() + ".update", sysBusparameter, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.update 失败:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysBusparameter), e);
        } catch (Exception e) {
            response = ResultInfo.error("业务参数编辑失败");
            logger.error("{}.update 出错:{},params:{}", getClass().getName(), e.getMessage(), JSONObject.toJSONString(sysBusparameter), e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "业务参数详情", value = "/getDetail", method = RequestMethod.GET)
    public ResultInfo getDetails(Long busparamid) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == busparamid || busparamid.equals(Constants.DEFAULT_LONG)) {
                return ResultInfo.error("参数错误");
            }
            response.setData(this.sysBusParamService.getDetail(busparamid));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getDetails 获取业务参数详情失败:{},params:{}", getClass().getName(), e.getMessage(), busparamid, e);
        } catch (Exception e) {
            response = ResultInfo.error("获取业务参数详情失败");
            logger.error("{}.getDetails 获取业务参数详情失败:{},params:{}", getClass().getName(), e.getMessage(), busparamid, e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "业务参数删除", value = "/delete", method = RequestMethod.POST)
    public ResultInfo delete(Long busparamid) {
        ResultInfo response = ResultInfo.ok();
        try {
            if (null == busparamid || busparamid == 0) {
                return ResultInfo.error("参数错误");
            }
            LoginUser loginAdmin = getLoginAdmin();
            this.sysBusParamService.doDeleteParam(busparamid, loginAdmin);
            LogUtils.logUserModifyOperates(getClass().getName() + ".delete", busparamid, loginAdmin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.delete 失败:{},params:{}", getClass().getName(), e.getMessage(), busparamid, e);
        } catch (Exception e) {
            response = ResultInfo.error("业务参数删除失败");
            logger.error("{}.delete 出错:{},params:{}", getClass().getName(), e.getMessage(), busparamid, e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "公告编辑", value = "/updateNote", method = RequestMethod.POST)
    public ResultInfo updateNovel(String noteInf) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            this.sysBusParamService.updateNote(noteInf);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("失败:{}", e.getMessage());
        }
        logger.info("/updateNovel耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @ResponseBody
    @RequestMapping(name = "获取公告", value = "/getNote", method = RequestMethod.GET)
    public ResultInfo getNovel() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.sysBusParamService.getNote());
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("失败:{}", e.getMessage());
        }
        logger.info("/updateNovel耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }
}