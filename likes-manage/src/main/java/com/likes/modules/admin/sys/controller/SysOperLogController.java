package com.likes.modules.admin.sys.controller;

import com.likes.common.BaseController;
import com.likes.common.annotation.Syslog;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.SysOperlog;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.request.SysOperLogRequest;
import com.likes.common.model.request.SysRecordRequest;
import com.likes.common.service.sys.SysOperLogService;
import com.likes.common.service.sys.SysRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sysoperlog")
public class SysOperLogController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SysOperLogService sysOperLogService;

    @ResponseBody
    @RequestMapping(name = "系统操作日志", value = "/list", method = RequestMethod.GET)
    public ResultInfo report(SysOperLogRequest req){
        long start = System.currentTimeMillis();
        ResultInfo response = new ResultInfo<>();
        try {
            response = ResultInfo.ok(sysOperLogService.list(req));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.sysoperlog/list分页查询日志记录失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("分页会员迁移记录出错");
            logger.error("{}.sysoperlog/list分页查询日志记录出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("sysoperlog/list耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "日志删除", value = "/batchDel", method = RequestMethod.GET)
    public ResultInfo deleteAccount(String ids) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUser = getLoginAdmin();
            response.setData(sysOperLogService.batchDel(ids,loginUser));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.info("/sysoperlog/batchDel:{}", e.getMessage());
        }
        logger.info("/sysoperlog/batchDel{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

}
