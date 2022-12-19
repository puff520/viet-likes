package com.likes.modules.admin.finance.controller;

import com.likes.common.BaseController;
import com.likes.common.annotation.Syslog;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.TraRechargemealReq;
import com.likes.common.util.LogUtils;
import com.likes.modules.admin.finance.service.ManageTraRechargemealService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/finance")
public class TraRechargemealController extends BaseController {
    private final static Logger LOGGER = LogManager.getLogger(TraRechargemealController.class);

    @Resource
    private ManageTraRechargemealService manageTraRechargemealService;

    @Syslog("创建套餐")
    @ResponseBody
    @RequestMapping(name = "创建套餐", value = "/doSaveTraRechargemeal", method = RequestMethod.POST)
    public ResultInfo doSetTraRechargemeal(@Valid TraRechargemealReq req, BindingResult err) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (err.hasErrors()) {
                throw new BusinessException(err.getAllErrors().get(0).getDefaultMessage());
            }
            LoginUser loginUser = getLoginAdmin();
            response.setData(manageTraRechargemealService.doSaveTraRechargemeal(req, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doSetTraRechargemeal", req, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            LOGGER.error("{}.doSaveTraRechargemeal创建套餐出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("创建套餐失败");
            LOGGER.error("{}.doSaveTraRechargemeal创建套餐出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        LOGGER.info("/doSaveTraRechargemeal耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

	/*
		@ResponseBody
		@RequestMapping(name = "编辑套餐", value = "/doUpdateTraRechargemeal", method = RequestMethod.POST)
		public ResultInfo doUpdateTraRechargemeal(@Valid TraRechargemealReq req, BindingResult err) {
			long start = System.currentTimeMillis();
			ResultInfo response = ResultInfo.ok();
			try {
				if (err.hasErrors()) {
					throw new BusinessException(err.getAllErrors().get(0).getDefaultMessage());
				}
				LoginUser loginUser = getLoginAdmin();
				response.setData(traRechargemealService.doUpdateTraRechargemeal(req, loginUser));
			} catch (BusinessException e) {
				response.setResultInfo(e.getCode(), e.getMessage());
				LOGGER.info("失败:{}", e.getMessage());
			}
			LOGGER.info("/doUpdateTraRechargemeal耗时{}毫秒：", (System.currentTimeMillis() - start));
			return response;
		}*/


    @Syslog("删除套餐")
    @ResponseBody
    @RequestMapping(name = "删除套餐", value = "/doDelTraRechargemeal", method = RequestMethod.POST)
    public ResultInfo doDelTraRechargemeal(TraRechargemealReq req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(manageTraRechargemealService.doDelTraRechargemeal(req, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doDelTraRechargemeal", req, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            LOGGER.error("{}.doDelTraRechargemeal删除套餐出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("删除套餐失败");
            LOGGER.error("{}.doDelTraRechargemeal删除套餐出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        LOGGER.info("/doDelTraRechargemeal耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "充值套餐管理", value = "/traRechargemealList", method = RequestMethod.GET)
    public ResultInfo traRechargemealList(TraRechargemealReq req, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(manageTraRechargemealService.traRechargemealList(req, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            LOGGER.error("{}.traRechargemealList充值套餐管理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("充值套餐管理失败");
            LOGGER.error("{}.traRechargemealList充值套餐管理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        LOGGER.info("/traRechargemealList耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

}
