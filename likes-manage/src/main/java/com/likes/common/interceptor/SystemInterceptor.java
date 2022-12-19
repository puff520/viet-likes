/**
 *
 */
package com.likes.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.annotation.AppAccess;
import com.likes.common.constant.Constants;
import com.likes.common.constant.ModuleConstant;
import com.likes.common.constant.RedisKeys;
import com.likes.common.constant.SystemTips;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.SysParameterEnum;
import com.likes.common.model.LoginUser;
import com.likes.common.mybatis.entity.SysErrorlog;
import com.likes.common.mybatis.entity.SysParameter;
import com.likes.common.service.sys.SysErrorlogService;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.BaseUtil;
import com.likes.common.util.redis.RedisBusinessUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author abu
 * @version 1.0
 * @Description
 * @revise
 * @time 2018年6月6日 下午5:15:50
 */
@Component
public class SystemInterceptor extends HandlerInterceptorAdapter {

    protected Logger logger = LogManager.getLogger(getClass());

    @Resource
    private SysParamService sysParamService;
    @Resource
    private SysErrorlogService sysErrorlogService;

    //请求开始时间
    private long requestStartTime;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "sign, token, clientType, version");
        //判断系统是否处于系统维护状态
        if (RedisBusinessUtil.exists(RedisKeys.LIVE_SYSTEM_MANAGE_MAINTENANCE_STATUS)) {
            BaseUtil.writerResponse(response, "500", "系统维护中，请等待系统维护完成再试");
            return false;
        }
        //RedisBusinessUtil.checkIn((long)(Math.random()*9000));
        requestStartTime = System.currentTimeMillis();
        String uri = BaseUtil.getRequestUri(request);
        String acctoken = request.getHeader(Constants.ACCTOKEN_KEY);
        // 如果header中没有acctoken 则去取获取参数
        if (StringUtils.isBlank(acctoken)) {
            acctoken = request.getParameter(Constants.ACCTOKEN_KEY);
        }
        String clientType = request.getHeader(Constants.CLIENT_TYPE_STRING);
        String userIP = request.getHeader(Constants.HEADER_USER_IP);
        String feginSign = request.getHeader(Constants.FEGIN_SIGN);
        logger.info("{} 请求开始 uri:{}, clientType:{}, userIP:{}", acctoken, uri, clientType, userIP);

        if (RedisBusinessUtil.isIpRestrict(userIP, sysParamService, acctoken)) {
            BaseUtil.writerResponse(response, StatusCode.IP_RESTRICT);
            return false;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        boolean beanHasAppAccess = handlerMethod.getBeanType().getAnnotation(AppAccess.class) != null;
        if (method.getAnnotation(AllowAccess.class) != null || beanHasAppAccess || isWhiteUri(uri)) {
            return super.preHandle(request, response, handler);
        } else if (StringUtils.isEmpty(acctoken)) {
            BaseUtil.writer401Response(response, SystemTips.USER_NOT_EXISTS_OR_EXPIRED);
            return false;
        }

        Object jsonstr = RedisBusinessUtil.get(acctoken);
        if (null == jsonstr) {
            logger.info("获取redis中的acctoken:{}失败,导致用户未登录或已过期", acctoken);
            BaseUtil.writer401Response(response, SystemTips.USER_NOT_EXISTS_OR_EXPIRED);
            return false;
        }

        Long sessiontime = Constants.TOKEN_EXPIRES;
        SysParameter sp = this.sysParamService.getByCode(SysParameterEnum.SESSION_TIME_BACK.name());
        if (sp != null) {
            sessiontime = Long.parseLong(sp.getSysparamvalue()) * 60;
        }
        RedisBusinessUtil.set(acctoken, jsonstr, sessiontime);
        LoginUser user = null;
        try {
            user = JSONObject.parseObject((String) jsonstr, LoginUser.class);
        } catch (Exception e) {
            logger.error("{}:{} parse LoginUser:{} occur error.", acctoken, uri, jsonstr, e);
        }

        if (user != null) {
            // 单点登录验证逻辑
            if (RedisBusinessUtil.get(user.getAccno()) == null) {
                logger.info("{}:{} newacctoken is empty.", acctoken, uri);
                BaseUtil.writer401Response(response, SystemTips.USER_NOT_EXISTS_OR_EXPIRED);
                return false;
            }
            if (!acctoken.equals(RedisBusinessUtil.get(user.getAccno()))) {
                RedisBusinessUtil.delete(acctoken);
                logger.info("{}:{} 账号已在别处登录，您已被迫下线！", acctoken, uri);
                BaseUtil.writer401Response(response, "您的帐号已在另一个设备上登录");
                return false;
            }

//            String Interfaceurl = request.getRequestURI();
//            if (!NoAuthInterface.noAuthInterfaceList().contains(Interfaceurl)) {
//                //如果不需要验证的接口 没有此接口 ；就用接口权限里面的接口再次比较
//                //TODO 预留 测试完后用加密的数据 解密
//                List<String> InterfaceUrls = JSONArray.parseArray(RedisBusinessUtil.get(RedisKeys.LIVE_ROLE_INTERFACES + user.getSysroleid()), String.class);
//                if (!InterfaceUrls.contains(Interfaceurl)) {
//                    logger.info(Interfaceurl + " ： 权限不足");
//                    response.sendError(403, "您没有访问该模块的权限");
//                    return false;
//                }
//            }

            RedisBusinessUtil.set(acctoken, jsonstr, sessiontime);
            RedisBusinessUtil.set(user.getAccno(), acctoken, sessiontime);
            request.getSession().setAttribute(Constants.ADMIN_LOGIN_INFO, user);
            //传递参数
            request.setAttribute(Constants.ATTR_USER_ID, user.getMemid());
            return super.preHandle(request, response, handler);
        } else if (org.apache.commons.lang3.StringUtils.isNotBlank(feginSign) && Constants.FEGIN_SIGN_VALUE.equals(feginSign)) {
            return super.preHandle(request, response, handler);
        } else {
            BaseUtil.writer401Response(response, SystemTips.USER_NOT_EXISTS_OR_EXPIRED);
            return false;
        }

    }

    /**
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String uri = BaseUtil.getRequestUri(request);
        String acctoken = request.getHeader(Constants.ACCTOKEN_KEY);
        String clientType = request.getHeader(Constants.CLIENT_TYPE_STRING);
        String userIP = request.getHeader(Constants.HEADER_USER_IP);
        logger.info("{} 请求结束 uri:{}, 用时：{}, clientType:{}, userIP:{}", acctoken, uri, System.currentTimeMillis() - requestStartTime, clientType, userIP);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 400：错误请求 服务器不理解请求的语法。
        // 500：服务器内部错误 服务器遇到错误，无法完成请求。
        // 501：尚未实施 服务器不具备完成请求的功能。例如，服务器无法识别请求方法时可能会返回此代码。
        // 503：服务不可用 服务器目前无法使用：由于超载或停机维护。通常，这只是暂时状态。
        if (response.getStatus() == 400 || response.getStatus() == 500 || response.getStatus() == 501 || response.getStatus() == 503) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            SysErrorlog err = new SysErrorlog();
            err.setSystemname(ModuleConstant.LIVE_APP);
            err.setModelname(method.getName());
            err.setOptcontent(response.getOutputStream().toString());
            err.setOptip(BaseUtil.getIpAddress(request));
            // 日志等级DDINFO： 正常normal 系统错误error
            err.setLevel(Constants.ERROR_LEVEL_ERROR);
            err.setServerip(BaseUtil.getServerIp());
            this.sysErrorlogService.insert(err);
        }
    }

    private boolean isWhiteUri(String uri) {
        Set<String> set = new HashSet();
        set.add("/media");
        set.add("/ueditor");
        set.add("/login");
        set.add("/error");
        set.add("/swagger");
        set.add("/api-docs");
        set.add("/sendShortMsg");
        set.add("/timeout");
        set.add("/getCaptchaImage");
        set.add("/qrcode");
        List<String> result = set.stream().filter(a -> uri.contains(a)).collect(Collectors.toList());
        return !CollectionUtils.isEmpty(result);
    }
}

