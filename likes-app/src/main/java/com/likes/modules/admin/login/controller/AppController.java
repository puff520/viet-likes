/**
 *
 */
package com.likes.modules.admin.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.BaseController;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.constant.Constants;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.SysParameterEnum;
import com.likes.common.enums.UniqueCodeEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.model.dto.pay.LoginRepayUserDO;
import com.likes.common.model.request.ActingUsersRequest;
import com.likes.common.model.request.UsersRequest;
import com.likes.common.service.code.UniqueCodeService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.BeanUtils;
import com.likes.common.util.LogUtils;
import com.likes.common.util.StringUtils;
import com.likes.common.util.redis.RedisBaseUtil;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.login.controller.service.AppLoginService;
import com.likes.modules.admin.mail.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 阿布 liveapp登录相关
 */
@RestController
@RequestMapping("/app")
@Api(value = "app登录")
public class AppController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private AppLoginService usersService;
    @Resource
    private SysParamService sysParamService;
    @Resource
    private UniqueCodeService uniqueCodeService;
    @Resource
    private MailService mailService;


    @RequestMapping(name = "发送邮件", value = "/sendMailCode", method = RequestMethod.POST)
    public ResultInfo sendMailCode(UsersRequest req, HttpServletRequest request, HttpSession session) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(usersService.sendMailCode(req, request));
            LogUtils.logUserModifyOperates(this.getClass().getName() + ".sendSmsCode", req, getLoginUserAPP());
        } catch (BusinessException e) {
            logger.error("{}.sendSmsCode 失败:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req), e);
            response.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("{}.sendSmsCode 出错:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req), e);
            return ResultInfo.error("发送邮件失败");
        }
        logger.info("/app sendSmsCode耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @RequestMapping(name = "注册", value = "/register", method = RequestMethod.POST)
    public ResultInfo register(UsersRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            req.setServerIp(getServerIp());
            req.setClintipadd(resolveIp(request));
            String codeKey =(String) request.getSession().getAttribute("AppConst.LOGIN_CODE_KEY");
            String cacheCode = RedisBaseUtil.get("imageCode:" + codeKey);
            if (null == codeKey|| StringUtils.isEmpty(req.getImageCode())) {
                throw new BusinessException(StatusCode.LIVE_ERROR_122.getCode(), "验证码为空");
            }
            if (!req.getImageCode().equalsIgnoreCase(cacheCode)) {
                throw new BusinessException(StatusCode.LIVE_ERROR_130006.getCode(), "验证码错误");
            }
            response.setData(usersService.register(req));
            LogUtils.logUserModifyOperates(this.getClass().getName() + ".register", req, getLoginUserAPP());
        } catch (BusinessException e) {
            logger.error("{}.register 失败:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req), e);
            response.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("{}.register 出错:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req), e);
            //return ResultInfo.error("注册失败,请稍后重试 ");
            response.setResultInfo("100000001", null);
        }
        logger.info("/app register耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(name = "登录", value = "/login", method = RequestMethod.POST)
    public ResultInfo login(UsersRequest req, HttpServletRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        LoginUser loginUser = null;
        try {
            String source = resolveClientType(request);
            req.setClintipadd(resolveIp(request));
            req.setServerIp(getServerIp());
            req.setClientPhoneModel(resolveClientPhoneModel(request));
            loginUser = usersService.login(req, source);
            response.setData(loginUser);
            logger.info("{}BB直播app：{},登录成功. result:{}", req.getEmail(), JSONObject.toJSONString(req), JSONObject.toJSONString(loginUser));
            LogUtils.logUserModifyOperates(this.getClass().getName() + ".login", req, getLoginUserAPP());
        } catch (BusinessException e) {
            logger.error("{}.login 失败:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req), e);
            response.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("{}.login 出错:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req), e);
            //return ResultInfo.error("登录失败,请稍后重试 ");
            response.setResultInfo("100000001", null);
        }
        logger.info("/app login耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }


    @RequestMapping(name = "登出", value = "/logout", method = RequestMethod.POST)
    public ResultInfo logout(HttpServletRequest request) {
        long start = System.currentTimeMillis();
        ResultInfo resultInfo = new ResultInfo();
        try {
            logger.info("进入登出方法");
            LoginUser org = getLoginUserAPP();
            RedisBusinessUtil.delete(org.getAccno(), org.getAcctoken());
            resultInfo = ResultInfo.ok("登出成功");
            LogUtils.logUserModifyOperates(getClass().getName() + ".logout", org);
        } catch (BusinessException e) {
            logger.error("{}.logout登出失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
            resultInfo.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            resultInfo = ResultInfo.fail("登出失败");
            logger.error("{}.logout登出出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
        }
        logger.info("/logout{}毫秒", (System.currentTimeMillis() - start));
        return resultInfo;
    }


    @RequestMapping(name = "忘记密码-修改密码", value = "/updatePassword", method = RequestMethod.POST)
    public ResultInfo updatePassword(UsersRequest req) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok();
        try {
            response.setData(usersService.updatePassword(req));
            LogUtils.logUserModifyOperates(this.getClass().getName() + ".paymentJctCallback", req, getLoginUserAPP());
        } catch (BusinessException e) {
            logger.error("{}.updatePassword 失败:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req), e);
            response.setResultInfo(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("{}.updatePassword 出错:{},params:{}", this.getClass().getName(), e.getMessage(), JSONObject.toJSON(req), e);
            return ResultInfo.error("修改密码失败,请稍后重试 ");
        }
        logger.info("/app updatePassword耗时{}毫秒", (System.currentTimeMillis() - start));
        return response;
    }

}
