package com.likes.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.likes.common.annotation.AllowAccess;
import com.likes.common.constant.Constants;
import com.likes.common.constant.RedisKeys;
import com.likes.common.constant.SystemTips;
import com.likes.common.enums.LoginUserTypeEnum;
import com.likes.common.enums.StatusCode;
import com.likes.common.enums.SysParameterEnum;
import com.likes.common.model.LoginUser;
import com.likes.common.mybatis.entity.SysParameter;
import com.likes.common.service.sys.SysParamService;
import com.likes.common.util.BaseUtil;
import com.likes.common.util.StringUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
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

    //请求开始时间
    private long requestStartTime;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "sign, token, clientType, version");

        requestStartTime = System.currentTimeMillis();
        String uri = BaseUtil.getRequestUri(request);
        String acctoken = request.getHeader(Constants.ACCTOKEN_KEY);
        String clientType = request.getHeader(Constants.CLIENT_TYPE_STRING);
        String userIP = request.getHeader(Constants.HEADER_USER_IP);
        String feginSign = request.getHeader(Constants.FEGIN_SIGN);
        String phoneModel = request.getHeader(Constants.CLIENT_PHONE_MODEL);
        logger.info("{} 请求开始 uri:{}, clientType:{}, phoneModel:{}, userIP:{}, params:{}, headers:{}", acctoken, uri, clientType, phoneModel, userIP, BaseUtil.getRequestParameters(request), BaseUtil.getHeaders(request));


        if (!(handler instanceof HandlerMethod)) {
            return false;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.getAnnotation(AllowAccess.class) != null || isWhiteUri(uri)) {
            return super.preHandle(request, response, handler);
        } else if (org.apache.commons.lang3.StringUtils.isNotBlank(feginSign) && Constants.FEGIN_SIGN_VALUE.equals(feginSign) && StringUtils.isEmpty(acctoken)) {
            return super.preHandle(request, response, handler);
        }  else if (StringUtils.isEmpty(acctoken)) {
            BaseUtil.writer401Response(response, SystemTips.USER_NOT_EXISTS_OR_EXPIRED);
            return false;
        }

        Object jsonstr = RedisBusinessUtil.get("agent"+acctoken);
        if (null == jsonstr) {
            BaseUtil.writer401Response(response, SystemTips.USER_NOT_EXISTS_OR_EXPIRED);
            return false;
        }

        Long sessiontime = Constants.TOKEN_EXPIRES;
        SysParameter sp = this.sysParamService.getByCode(SysParameterEnum.SESSION_TIME.name());
        if (sp != null) {
            sessiontime = Long.parseLong(sp.getSysparamvalue()) * 60;
        }

        LoginUser user = JSONObject.parseObject((String) jsonstr, LoginUser.class);
        if (user != null && LoginUserTypeEnum.isUserType(user.getLogintype())) {
            String newacctoken = RedisBusinessUtil.get("agent"+user.getAccno());
            // 单点登录验证逻辑
            if (StringUtils.isEmpty(newacctoken)) {
                logger.info("{}:{} newacctoken is empty.", acctoken, uri);
                BaseUtil.writer401Response(response, SystemTips.USER_NOT_EXISTS_OR_EXPIRED);
                return false;
            }
            if (!acctoken.equals(newacctoken)) {
                BaseUtil.writer401Response(response, "您的帐号已在另一个设备上登录");
                RedisBusinessUtil.delete("agent"+acctoken);
                return false;
            }
            RedisBusinessUtil.checkIn(user.getMemid());
            RedisBusinessUtil.set("agent"+acctoken, jsonstr, sessiontime);
            RedisBusinessUtil.set("agent"+user.getAccno(), acctoken, sessiontime);

            request.getSession().setAttribute(Constants.APP_LOGIN_AGENT_INFO, user);

            //传递参数
            request.setAttribute(Constants.ATTR_AGENT_USER_ID, user.getMemid());
            return super.preHandle(request, response, handler);
        } else if (org.apache.commons.lang3.StringUtils.isNotBlank(feginSign) && Constants.FEGIN_SIGN_VALUE.equals(feginSign)) {
            return super.preHandle(request, response, handler);
        } else if (user != null && LoginUserTypeEnum.isRepayUser(user.getLogintype())) {
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

    private boolean isWhiteUri(String uri) {
        Set<String> set = new HashSet();
        set.add("/media");
        set.add("/app/updatePassword");
        set.add("/app/login");
        set.add("/app/register");
        set.add("/app/sendMailCode");
        set.add("/ueditor");
        set.add("/error");
        set.add("/swagger");
        set.add("/api-docs");
        set.add("/queryLotteryVersionZIP");
        set.add("/sendDeviceIdIp");
        set.add("/youkeList");
        set.add("/favoriteList");
        set.add("/all/list");
        set.add("/getSgPushAddress");
        set.add("/memLevelConfigAlllist");
        List<String> result = set.stream().filter(a -> uri.contains(a)).collect(Collectors.toList());
        return !CollectionUtils.isEmpty(result);
    }
}
