package com.likes.common.util;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.constant.Constants;
import com.likes.common.model.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: LogUtils
 * Description: 描述
 *
 * @author hai
 * @since JDK 1.8
 * date: 2020/3/6 12:43
 */
public class LogUtils {

    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);

    public static LoginUser getOperater(HttpServletRequest request) {
        return (LoginUser) request.getSession().getAttribute(Constants.ADMIN_LOGIN_INFO);
    }

    ////////////////////////////////// log 所有修改类操作（前后端都可用），只log不入库，做后续问题跟踪用 //////////////////////////////////
    public static void logModifyOperation(Object userId, String method, Object params) {
        logModifyOperation(userId, method, JSONObject.toJSONString(params));
    }

    public static void logModifyOperation(Object userId, String method, String params) {
        if (null == userId || "".equals(userId.toString())) {
            logger.info("modify log method:{} params:{} local address:{}", method, params, Constants.LOCAL_ADDRESS);
        } else {
            logger.info("modify log {} method:{} params:{} local address:{}", userId, method, params, Constants.LOCAL_ADDRESS);
        }
    }

    public static void logUserModifyOperates(String method, Object content, LoginUser loginUser) {
        logUserModifyOperates(method, JSONObject.toJSONString(content), loginUser);
    }

    public static void logUserModifyOperates(String method, String content, LoginUser loginUser) {
        String account = null;
        Long accountId = null;
        if (null != loginUser) {
            account = loginUser.getAccno();
            accountId = loginUser.getMemid();
        }
        logger.info("user oplog {}:{}, id={}, content:{} localAddress:{}", account, method, accountId, content, Constants.LOCAL_ADDRESS);
    }

    ////////////////////////////////// log 敏感类查询操作（前后端都可用），只log不入库，做后续问题跟踪用 //////////////////////////////////
    public static void logQueryOperation(Object userId, String method, Object params) {
        logQueryOperation(userId, method, JSONObject.toJSONString(params));
    }

    public static void logQueryOperation(Object userId, String method, String params) {
        if (null == userId || "".equals(userId.toString())) {
            logger.info("query log method:{} params:{} local address:{}", method, params, Constants.LOCAL_ADDRESS);
        } else {
            logger.info("query log {} method:{} params:{} local address:{}", userId, method, params, Constants.LOCAL_ADDRESS);
        }
    }

    public static void logUserModifyOperates(String method, LoginUser loginUser) {
        String account = null;
        Long accountId = null;
        if (null != loginUser) {
            account = loginUser.getAccno();
            accountId = loginUser.getMemid();
        }
        logger.info("admin oplog {}:{}, id={}, localAddress:{}", account, method, accountId, Constants.LOCAL_ADDRESS);
    }

    public static void logUserQueryOperates(String method, Object content, LoginUser loginUser) {
        logUserQueryOperates(method, JSONObject.toJSONString(content), loginUser);
    }

    public static void logUserQueryOperates(String method, String content, LoginUser loginUser) {
        String account = null;
        Long accountId = null;
        if (null != loginUser) {
            account = loginUser.getAccno();
            accountId = loginUser.getMemid();
        }
        logger.info("user querylog {}:{}, id={}, content:{} localAddress:{}", account, method, accountId, content, Constants.LOCAL_ADDRESS);
    }

}
