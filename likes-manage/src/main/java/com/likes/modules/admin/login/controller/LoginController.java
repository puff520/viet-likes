package com.likes.modules.admin.login.controller;

import com.likes.common.BaseController;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.annotation.Syslog;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.util.DateUtils;
import com.likes.common.util.LogUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import com.likes.modules.admin.login.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * @author abu
 * @version 1.0
 * @Description 运营登录
 * @revise
 * @time 2018年6月8日 下午3:25:23
 */
@RestController
@Api("登录")
public class LoginController extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private LoginService loginService;


    @ApiOperation(value="后台登录", notes="后台登录接口")
    @RequestMapping(name = "登录", value = "/login", method = RequestMethod.POST )
    public ResultInfo login(String acclogin, String password, String vercode) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok("登录成功");
        try {
            LoginUser org = loginService.doLogin(acclogin, password, resolveIp(request), getServerIp());
            response.setData(org);
            LogUtils.logUserModifyOperates(getClass().getName() + ".registerAnchor", acclogin, null);
            logger.info("BB直播后台：{},登录成功", acclogin);
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.login 失败:{}, params:{}", getClass().getName(), e.getMessage(), acclogin, e);
        } catch (Exception e) {
            response = ResultInfo.fail("系统错误");
            logger.error("{}.login 出错:{}, params:{}", getClass().getName(), e.getMessage(), acclogin, e);
        }
        logger.info("/BB直播后台login耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }



    @RequestMapping(name = "登出", value = "/logout", method = RequestMethod.POST)
    public ResultInfo logout(HttpSession session) {
        long start = System.currentTimeMillis();
        ResultInfo resultInfo = new ResultInfo();
        try {
            logger.info("进入登出方法");
            LoginUser org = getLoginAdmin();
            if (ObjectUtils.isEmpty(org)) {
                return ResultInfo.fail("用户登录失效");
            }
            RedisBusinessUtil.delete(org.getAccno(), org.getAcctoken());
            //删除在线用户缓存，统计在线人数时候用到
            String clientType = resolveClientType(request);
            if (org.getLoginid() != null && org.getLoginid().longValue() > 0) {
                List<String> delList = new ArrayList<>();
                delList.add(org.getLoginid().toString());
                RedisBusinessUtil.delMemberOnline(clientType, delList);
            }
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

    /**
     * @param newpsd
     * @param oldpsd
     * @return
     * @author abu
     * <p>
     * Description:<br>
     * 修改密码
     */
    @Syslog("修改密码")
    @RequestMapping(name = "修改密码", value = "/modpassword", method = RequestMethod.POST)
    public ResultInfo repassword(String newpsd, String oldpsd) {
        long start = System.currentTimeMillis();
        ResultInfo response = ResultInfo.ok("修改密码成功");
        LoginUser user = this.getLoginAdmin();
        try {
            this.loginService.doModPassword(newpsd, oldpsd, user);
            logger.info("{}修改密码成功", user.getAccno());
        } catch (BusinessException e) {
            response.setResultInfo(e.getCode(), e.getMessage());
            logger.error("{}.repassword修改密码失败,用户账号:{}修改密码失败信息:{}", getClass().getName(), user.getAccno(), e.getMessage(), e);
        } catch (Exception e) {
            response = ResultInfo.fail("修改密码失败");
            logger.error("{}.repassword修改密码出错,用户账号:{},修改密码失败信息:{}", getClass().getName(), user.getAccno(), e.getMessage(), e);
        }
        logger.info("/modpassword耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

    @AllowAccess
    @RequestMapping(name = "现在时间", value = "/now", method = RequestMethod.GET)
    public ResultInfo now(HttpSession session) throws Exception {
        ResultInfo response = new ResultInfo();
        long start = System.currentTimeMillis();
        try {
            String nowtime = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
            RedisBusinessUtil.set("nowtime", nowtime, 120l);
            Thread.sleep(1);
            response = ResultInfo.ok(RedisBusinessUtil.get("nowtime"));
            logger.info("{}.now获取现在时间成功", getClass().getName());
        } catch (BusinessException e) {
            logger.error("{}.now获取现在时间失败,失败信息:{}", getClass().getName(), e.getMessage(), e);
        } catch (Exception e) {
            logger.error("{}.now获取现在时间出错,出错信息:{}", getClass().getName(), e.getMessage(), e);
            response = ResultInfo.fail("获取现在时间出错");
        }
        logger.info("/now耗时{}毫秒：", (System.currentTimeMillis() - start));
        return response;
    }

}
