package com.likes.modules.admin.users.controller;

import com.likes.common.BaseController;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.mybatis.mapper.MemberCreditDetailMapper;
import com.likes.common.service.member.MemLevelConfigService;
import com.likes.common.service.sys.InfSysnoticeService;
import com.likes.common.util.LogUtils;
import com.likes.common.util.encrypt.AESUtils;
import com.likes.modules.admin.users.service.UsersService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UsersController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UsersService usersService;

    @Resource
    private InfSysnoticeService infSysnoticeService;



        @AllowAccess
        @ResponseBody
        @RequestMapping(name = "获取系统参数值", value = "/getSysParam", method = RequestMethod.GET)
        public ResultInfo getSysParam(String code) {
            long start = System.currentTimeMillis();
            ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.usersService.getSysParam(code));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getSysParam 失败:{}, params:{}", getClass().getName(), e.getMessage(), code, e);
        } catch (Exception e) {
            response = ResultInfo.error("获取系统参数值出错");
            logger.error("{}.getSysParam 出错:{}, params:{}", getClass().getName(), e.getMessage(), code, e);
        }
        logger.info("/getSysParam耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @AllowAccess
    @ResponseBody
    @RequestMapping(name = "获取系统公告", value = "/getSysNotice", method = RequestMethod.GET)
    public ResultInfo getSysNotice(Integer type) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(this.infSysnoticeService.selectAPPNoticeList(type));
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getSysParam 失败:{}, params:{}", getClass().getName(), e.getMessage(), type, e);
        } catch (Exception e) {
            response = ResultInfo.error("获取系统参数值出错");
            logger.error("{}.getSysParam 出错:{}, params:{}", getClass().getName(), e.getMessage(), type, e);
        }
        logger.info("/getSysParam耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @ResponseBody
    @RequestMapping(name = "app访问链路选择", value = "/getLink")
    public ResultInfo getLink() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            LoginUser loginUserAPP = getLoginUserAPP();
            response.setData(this.usersService.getLink(loginUserAPP));
            LogUtils.logUserModifyOperates(getClass().getName() + ".registerAnchor", loginUserAPP);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.getLink 失败:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.error("app访问链路选择出错");
            logger.error("{}.getLink 出错:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/getLink耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @AllowAccess
    @ResponseBody
    @RequestMapping(name = "H5点击邀请链接", value = "/invite/{param}", method = RequestMethod.GET)
    public ResultInfo invite(@PathVariable("param") String param) throws IOException {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            if (StringUtils.isEmpty(param) || "null".equals(param)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_141.getCode(), "邀请码为空");
            }
            String invitecode = AESUtils.decryptData(param, AESUtils.KEY);
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("invitecode", invitecode);
            response.setData(dataMap);
        } catch (Exception e) {
            response = ResultInfo.error("H5点击邀请链接出错");
            logger.error("{}.invite 出错:{}, params:{}", getClass().getName(), e.getMessage(), param, e);
        }
        logger.info("/invite耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @AllowAccess
    @ResponseBody
    @RequestMapping(name = "APP下载链接", value = "/appdownurl", method = RequestMethod.GET)
    public ResultInfo appdownurl() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(usersService.appdownurl());
        } catch (Exception e) {
            response = ResultInfo.error("H5点击邀请链接出错");
            logger.error("{}.appdownurl 出错:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/appdownurl耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

    @AllowAccess
    @ResponseBody
    @RequestMapping(name = "app更新", value = "/updateapp", method = RequestMethod.GET)
    public ResultInfo updateapp() {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(usersService.updateapp());
        } catch (Exception e) {
            response = ResultInfo.error("app更新出错");
            logger.error("{}.updateapp 出错:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/updateapp耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }



}
