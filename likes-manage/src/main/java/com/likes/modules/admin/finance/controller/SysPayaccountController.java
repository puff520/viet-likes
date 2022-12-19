package com.likes.modules.admin.finance.controller;

import com.likes.common.BaseController;
import com.likes.common.annotation.Syslog;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageBounds;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.mybatis.entity.SysPayaccount;
import com.likes.common.mybatis.entity.SysPayprovider;
import com.likes.common.mybatis.entity.SysThreepayset;
import com.likes.common.util.LogUtils;
import com.likes.modules.admin.finance.service.ManageSysPayaccountService;
import com.likes.modules.admin.finance.service.SysThreepaysetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@RequestMapping(value = "/payaccount")
@Controller
public class SysPayaccountController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ManageSysPayaccountService manageSysPayaccountService;
    @Resource
    private SysThreepaysetService sysThreepaysetService;

    @ResponseBody
    @RequestMapping(name = "公司入款设定", value = "/list", method = RequestMethod.GET)
    public ResultInfo list(SysPayaccount sysPayaccount, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.manageSysPayaccountService.getList(sysPayaccount, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.list公司入款设定出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("公司入款设定失败");
            logger.error("{}.list公司入款设定出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/list耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("收款账户新增")
    @ResponseBody
    @RequestMapping(name = "收款账户新增", value = "/doSavePayaccount", method = RequestMethod.POST)
    public ResultInfo doSavePayaccount(SysPayaccount sysPayaccount) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.manageSysPayaccountService.doSavePayaccount(sysPayaccount, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doSavePayaccount", sysPayaccount, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doSavePayaccount收款账户新增出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("收款账户新增失败");
            logger.error("{}.doSavePayaccount收款账户新增出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/doSavePayaccount耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("收款账户编辑")
    @ResponseBody
    @RequestMapping(name = "收款账户编辑", value = "/doUpdatePayaccount", method = RequestMethod.POST)
    public ResultInfo doUpdatePayaccount(SysPayaccount sysPayaccount) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.manageSysPayaccountService.doUpdatePayaccount(sysPayaccount, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doUpdatePayaccount", sysPayaccount, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doUpdatePayaccount收款账户编辑出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("收款账户编辑失败");
            logger.error("{}.doUpdatePayaccount收款账户编辑出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/doUpdatePayaccount耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("收款账户启用禁用")
    @ResponseBody
    @RequestMapping(name = "收款账户启用禁用", value = "/doUpdateStatus", method = RequestMethod.POST)
    public ResultInfo doUpdateStatus(SysPayaccount sysCdn) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.manageSysPayaccountService.doUpdateStatus(sysCdn, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doUpdateStatus", sysCdn, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doUpdateStatus收款账户启用禁用出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("收款账户启用禁用失败");
            logger.error("{}.doUpdateStatus收款账户启用禁用出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/doUpdateStatus耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("收款账户清零")
    @ResponseBody
    @RequestMapping(name = "收款账户清零", value = "/clean", method = RequestMethod.POST)
    public ResultInfo clean(SysPayaccount sysCdn) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.manageSysPayaccountService.cleanTotalAmount(sysCdn, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doUpdateStatus", sysCdn, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doUpdateStatus收款账户启用禁用出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("收款账户启用禁用失败");
            logger.error("{}.doUpdateStatus收款账户启用禁用出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/doUpdateStatus耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("收款账户删除")
    @ResponseBody // 收款账户删除
    @RequestMapping(name = "收款账户删除", value = "/doDelPayaccount", method = RequestMethod.POST)
    public ResultInfo doDelPayaccount(SysPayaccount sysPayaccount) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.manageSysPayaccountService.doDelPayaccount(sysPayaccount, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doDelPayaccount", sysPayaccount, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doDelPayaccount收款账户删除出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("收款账户删除失败");
            logger.error("{}.doDelPayaccount收款账户删除出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/doDelPayaccount耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    // -----------------------------第三方支付商家---------------------------------------
    @Syslog("线上入款设定新增")
    @ResponseBody
    @RequestMapping(name = "线上入款设定新增", value = "/doSaveSysThreepayset", method = RequestMethod.POST)
    public ResultInfo doSaveSysThreepayset(SysThreepayset sysThreepayset) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysThreepaysetService.doSaveSysThreepayset(sysThreepayset, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doSaveSysThreepayset", sysThreepayset, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doSaveSysThreepayset线上入款设定新增出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("线上入款设定新增失败");
            logger.error("{}.doSaveSysThreepayset线上入款设定新增出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/doSaveSysThreepayset耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("线上入款设定编辑")
    @ResponseBody
    @RequestMapping(name = "线上入款设定编辑", value = "/doUpdateSysThreepayset", method = RequestMethod.POST)
    public ResultInfo doUpdateSysThreepayset(SysThreepayset sysThreepayset) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysThreepaysetService.doUpdateSysThreepayset(sysThreepayset, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doUpdateSysThreepayset", sysThreepayset, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doUpdateSysThreepayset线上入款设定编辑出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("线上入款设定编辑失败");
            logger.error("{}.doUpdateSysThreepayset线上入款设定编辑出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/doUpdateSysThreepayset耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("线上入款设定删除")
    @ResponseBody
    @RequestMapping(name = "线上入款设定删除", value = "/doDelSysThreepayset", method = RequestMethod.POST)
    public ResultInfo doDelSysThreepayset(SysThreepayset sysPayaccount) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysThreepaysetService.doDelSysThreepayset(sysPayaccount, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doDelSysThreepayset", sysPayaccount, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doDelSysThreepayset线上入款设定删除出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("线上入款设定删除失败");
            logger.error("{}.doDelSysThreepayset线上入款设定删除出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/doDelSysThreepayset耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("线上入款设定启用禁用")
    @ResponseBody
    @RequestMapping(name = "线上入款设定启用禁用", value = "/doUpdateSysThreepaysetStatus", method = RequestMethod.POST)
    public ResultInfo doUpdateSysThreepaysetStatus(SysThreepayset sysThreepayset) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysThreepaysetService.doUpdateSysThreepaysetStatus(sysThreepayset, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doUpdateSysThreepaysetStatus", sysThreepayset, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doUpdateSysThreepaysetStatus线上入款设定启用禁用出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("线上入款设定启用禁用失败");
            logger.error("{}.doUpdateSysThreepaysetStatus线上入款设定启用禁用出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/doUpdateSysThreepaysetStatus耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("线上入款设定")
    @ResponseBody
    @RequestMapping(name = "线上入款设定", value = "/sysThreepaysetlist", method = RequestMethod.GET)
    public ResultInfo sysThreepaysetlist(SysThreepayset sysThreepayset, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.sysThreepaysetService.sysThreepaysetlist(sysThreepayset, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.sysThreepaysetlist线上入款设定出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("线上入款设定失败");
            logger.error("{}.sysThreepaysetlist线上入款设定出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/sysThreepaysetlist耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("支付商管理")
    @ResponseBody
    @RequestMapping(name = "支付商管理", value = "/sysPayproviderlist", method = RequestMethod.GET)
    public ResultInfo sysPayproviderlist(SysPayprovider sysPayprovider, PageBounds page) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.sysThreepaysetService.getSysPayproviderList(sysPayprovider, page));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.sysPayproviderlist支付商管理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("支付商管理失败");
            logger.error("{}.sysPayproviderlist支付商管理出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/sysPayproviderlist耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "支付商列表", value = "/getAllsysPayproviderlist", method = RequestMethod.GET)
    public ResultInfo getAllsysPayproviderlist(SysPayprovider sysPayprovider) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.sysThreepaysetService.getAllsysPayproviderlist(sysPayprovider));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getAllsysPayproviderlist支付商列表出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("支付商列表失败");
            logger.error("{}.getAllsysPayproviderlist支付商列表出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getAllsysPayproviderlist耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("支付商新增")
    @ResponseBody
    @RequestMapping(name = "支付商新增", value = "/doSaveSysPayprovider", method = RequestMethod.POST)
    public ResultInfo doSaveSysPayprovider(SysPayprovider sysPayprovider) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysThreepaysetService.doSaveSysPayprovider(sysPayprovider, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doSaveSysPayprovider", sysPayprovider, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doSaveSysPayprovider支付商新增出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("支付商新增失败");
            logger.error("{}.doSaveSysPayprovider支付商新增出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/doSaveSysPayprovider耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("支付商编辑")
    @ResponseBody
    @RequestMapping(name = "支付商编辑", value = "/doUpdateSysPayprovider", method = RequestMethod.POST)
    public ResultInfo doUpdateSysPayprovider(SysPayprovider sysPayprovider) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysThreepaysetService.doUpdateSysPayprovider(sysPayprovider, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doUpdateSysPayprovider", sysPayprovider, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doUpdateSysPayprovider支付商编辑出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("支付商编辑失败");
            logger.error("{}.doUpdateSysPayprovider支付商编辑出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/doUpdateSysPayprovider耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("支付商删除")
    @ResponseBody
    @RequestMapping(name = "支付商删除", value = "/doDelSysPayprovider", method = RequestMethod.POST)
    public ResultInfo doDelSysPayprovider(SysPayprovider sysPayprovider) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysThreepaysetService.doDelSysPayprovider(sysPayprovider, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doDelSysPayprovider", sysPayprovider, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doDelSysPayprovider支付商删除出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("支付商删除失败");
            logger.error("{}.doDelSysPayprovider支付商删除出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/doDelSysPayprovider耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @Syslog("支付商启用禁用")
    @ResponseBody
    @RequestMapping(name = "支付商启用禁用", value = "/doUpdateSysPayproviderStatus", method = RequestMethod.POST)
    public ResultInfo doUpdateSysPayproviderStatus(SysPayprovider sysPayprovider) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(this.sysThreepaysetService.doUpdateSysPayproviderStatus(sysPayprovider, loginUser));
            LogUtils.logUserModifyOperates(getClass().getName() + ".doUpdateSysPayproviderStatus", sysPayprovider, loginUser);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.doUpdateSysPayproviderStatus支付商启用禁用出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("支付商启用禁用失败");
            logger.error("{}.doUpdateSysPayproviderStatus支付商启用禁用出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/doUpdateSysPayproviderStatus耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


}
