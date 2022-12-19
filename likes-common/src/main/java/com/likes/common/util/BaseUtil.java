package com.likes.common.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.likes.common.constant.Constants;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.StatusCode;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.RequestInfo;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.util.encrypt.AESUtil;
import com.likes.common.util.http.HttpRespons;
import com.likes.common.util.http.HttpUtils;
import com.likes.common.util.redis.RedisBusinessUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * @Author: admin
 * @Description:基础工具类
 * @Version: 1.0.0
 * @Date; 2017-12-20 10:53
 */
public class BaseUtil {
    private static final Logger logger = LoggerFactory.getLogger(BaseUtil.class);
    // 验证码字符集
    private static final char[] chars = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * @param size 随机数长度
     * @Author: admin
     * @Description: 获取随机数
     * @Version: 1.0.0
     * @Date; 2018/4/25 16:12
     * @return: java.lang.String
     */
    public static String getRandomNumber(int size) {
        StringBuffer sb = new StringBuffer();
        Random ran = new Random();
        for (int i = 0; i < size; i++) {
            // 取随机字符索引
            int n = ran.nextInt(chars.length);
            // 记录字符
            sb.append(chars[n]);
        }
        return sb.toString();
    }

    /**
     * PARAM 检查参数是否有误
     *
     * @param object
     * @param resultInfo
     * @return
     */
    public static boolean checkRequestInfo(Object object, ResultInfo resultInfo) {
        RequestInfo<Object> requestInfo = (RequestInfo<Object>) object;

        if (requestInfo == null || requestInfo.getData() == null) {
            resultInfo.setStatus(StatusCode.PARAM_ERROR.getCode());
            resultInfo.setInfo(StatusCode.PARAM_ERROR.getValue());
            return true;
        }
        return false;
    }

    /**
     * @param request
     * @Author: admin
     * @Description: nginx代理后获取ip方法
     * @Version: 1.0.0
     * @Date; 2018/5/17 9:31
     * @return: java.lang.String
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    public static String getServerIp() {
        // 获取操作系统类型
        try {
            HttpRespons hr = HttpUtils.sendGet("http://ifconfig.me");
            int code = hr.code;
            if (code == 200) {
                return hr.content.trim();
            } else {
                return "获取外网ip地址失败";
            }
        } catch (Exception e) {
            logger.error("获取外网ip地址失败", e);
            return e.getMessage();
        }
    }

    /**
     * 根据网络接口获取IP地址
     *
     * @param ethNum 网络接口名，Linux下是eth0
     * @return
     */
    @SuppressWarnings({"unused", "rawtypes"})
    public static String getIpByEthNum(String ethNum) {
        try {
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (ethNum.equals(netInterface.getName())) {
                    Enumeration addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = (InetAddress) addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            logger.error("getIpByEthNum occur error", e);
        }
        return "获取服务器IP错误";
    }

    public static String getUserIp(HttpServletRequest request) {
        String userIp = request.getHeader(Constants.HEADER_USER_IP);
        if (StringUtils.isNotEmpty(userIp)) {
            return userIp;
        }
        userIp = request.getParameter(Constants.HEADER_USER_IP);
        if (StringUtils.isNotEmpty(userIp)) {
            return userIp;
        }
        return getIpAddress(request);
    }

    private static void logIp(String ip, HttpServletRequest request) {
        StringBuilder ipLog = new StringBuilder();
        ipLog.append("X-forwarded-for:").append(request.getHeader("x-forwarded-for")).append("; ");
        ipLog.append("Proxy-Client-IP:").append(request.getHeader("Proxy-Client-IP")).append("; ");
        ipLog.append("WL-Proxy-Client-IP:").append(request.getHeader("WL-Proxy-Client-IP")).append("; ");
        ipLog.append("HTTP_CLIENT_IP:").append(request.getHeader("HTTP_CLIENT_IP")).append("; ");
        ipLog.append("HTTP_X_FORWARDED_FOR:").append(request.getHeader("HTTP_X_FORWARDED_FOR")).append("; ");
        ipLog.append("X-Real-IP:").append(request.getHeader("X-Real-IP")).append("; ");
        ipLog.append("Request.getRemoteAddr:").append(request.getRemoteAddr()).append("; ");
        ipLog.append("Ip:").append(ip).append("; ");
        logger.info(ipLog.toString());
    }

    /**
     * @param data 待加密数据
     * @Author: admin
     * @Description: AES 加密
     * @Version: 1.0.0
     * @Date; 2018/4/23 17:19
     * @return: java.lang.String
     */
    public static String AesEncrypt(String data) {
        return AESUtil.encrypt(data, Constants.AES_KEY);
    }

    /**
     * @param data 待解密数据
     * @Author: admin
     * @Description: AES 解密
     * @Version: 1.0.0
     * @Date; 2018/4/23 17:19
     * @return: java.lang.String
     */
    public static String AesDecrypt(String data) {
        return AESUtil.decrypt(data, Constants.AES_KEY);
    }

    public static String getRequestUri(HttpServletRequest request) {
        String uri = request.getRequestURI();
        uri = request.getQueryString() != null ? uri + "?" + request.getQueryString() : uri;
        return uri;
    }

    public static void writerResponse(HttpServletResponse response, StatusCode code) throws IOException {
        writerResponse(response, code.getCode(), code.getValue());
    }

    public static void writer401Response(HttpServletResponse response, String msg) throws IOException {
        writerResponse(response, StatusCode.LIVE_ERROR_401.getCode(), msg);
    }

    public static void writerResponse(HttpServletResponse response, String code, String msg) throws IOException {
        writerResponse(response, code, msg, null);
    }

    public static void writerResponse(HttpServletResponse response, String code, String msg, Object data) throws IOException {
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        ResultInfo resultInfo = new ResultInfo<>();
        resultInfo.setStatus(code);
        resultInfo.setInfo(msg);
        resultInfo.setData(data);
        response.getWriter().print(JSON.toJSONString(resultInfo, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteMapNullValue));
    }

    public static void logAllParameters(HttpServletRequest request) {
        logAllParameters(request, null);
    }

    public static void logAllParameters(HttpServletRequest request, String prefix) {
        logParameters(request, prefix);
        //logRequestBody(request, prefix);
        logHeaders(request, prefix);
    }

    public static void logParameters(HttpServletRequest request) {
        logParameters(request, null);
    }

    public static void logParameters(HttpServletRequest request, String prefix) {
        String params = getRequestParameters(request);
        if (StringUtils.isEmpty(prefix)) {
            logger.info("{} parameters:{}", request.getRequestURI(), params);
        } else {
            logger.info("{} {} parameters:{}", prefix, request.getRequestURI(), params);
        }
    }

    public static void logHeaders(HttpServletRequest request) {
        logHeaders(request, null);
    }

    public static void logHeaders(HttpServletRequest request, String prefix) {
        String headers = getHeaders(request);
        if (StringUtils.isEmpty(prefix)) {
            logger.info("{} headers:{}", request.getRequestURI(), headers);
        } else {
            logger.info("{} {} headers:{}", prefix, request.getRequestURI(), headers);
        }
    }

    public static void logRequestBody(HttpServletRequest request) {
        logRequestBody(request, null);
    }

    public static void logRequestBody(HttpServletRequest request, String prefix) {
        String body = getRequestBody(request);
        if (StringUtils.isEmpty(prefix)) {
            logger.info("{} requestBody:{}", request.getRequestURI(), body);
        } else {
            logger.info("{} {} requestBody:{}", prefix, request.getRequestURI(), body);
        }
    }

    public static String getRequestParameters(HttpServletRequest request) {
        Map<String, String[]> parameters = request.getParameterMap();
        StringBuilder parameterStr = new StringBuilder();
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            StringBuilder value = new StringBuilder();
            for (String val : entry.getValue()) {
                value.append(val).append(",");
            }
            parameterStr.append(entry.getKey()).append("=").append(value.substring(0, value.length() - 1)).append("\n");
        }
        String log = null;
        if (parameterStr.length() > 0) {
            log = parameterStr.substring(0, parameterStr.length() - 1);
        }
        return log;
    }

    /**
     * 从request中获得参数Map，并返回可读的Map
     *
     * @param request
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Map getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    public static String getHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder headerStr = new StringBuilder();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            headerStr.append(name).append("=").append(value).append(";");
        }
        String log = null;
        if (headerStr.length() > 0) {
            log = headerStr.substring(0, headerStr.length() - 1);
        }
        return log;
    }

    public static String getRequestBody(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null && inputStream.available() > 0) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
            }
        } catch (IOException ex) {
            logger.error("{} getRequestBody occur error.", request.getRequestURI(), ex);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    logger.error("{} getRequestBody close reader occur error.", request.getRequestURI(), ex);
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * @return
     * @author abu
     * <p>
     * Description:<br>
     * APP用户登录
     */
    public static LoginUser getLoginUserAPP(HttpServletRequest request) {
        try {
            return (LoginUser) request.getSession().getAttribute(Constants.APP_LOGIN_INFO);
        } catch (Exception e) {
            logger.error("getLoginUserAPP ocuur error", e);
            return null;
        }
    }

    /**
     * @return
     * @author abu
     * <p>
     * Description:<br>
     * 运营后台登录
     */
    public static LoginUser getLoginAdmin(HttpServletRequest request) {
        try {
            return (LoginUser) request.getSession().getAttribute(Constants.ADMIN_LOGIN_INFO);
        } catch (Exception e) {
            logger.error("getLoginAdmin ocuur error", e);
            return null;
        }
    }

    /**
     * 维护状态下是否在白名单
     *
     * @param acctoken
     * @param userIp
     * @param request
     * @param response
     * @return
     */
    public static boolean isNotExistsWhiteIp(String acctoken, String userIp, HttpServletRequest request, HttpServletResponse response) {
        userIp = StringUtils.isEmpty(userIp) ? BaseUtil.getIpAddress(request) : userIp;
        Map<String, String> maintenanceData = RedisBusinessUtil.get(RedisKeys.LIVE_SYSTEM_MAINTENANCE_STATUS);
        maintenanceData = null == maintenanceData ? new HashMap<>() : maintenanceData;
        String ipList = maintenanceData.get("ipList");
        ipList = null == ipList ? "" : ipList;
        if (!ipList.contains(userIp)) {
            logger.info("{} isNotExistsWhiteIp {}, ipList:{}", acctoken, userIp, ipList);
            try {
                maintenanceData.remove("ipList");
                BaseUtil.writerResponse(response, StatusCode.SYSTEM_MAINTENANCE_CODE.getCode(), "系统维护中，请等待系统维护完成再试", maintenanceData);
                return true;
            } catch (IOException e) {
                logger.error("{} isNotExistsWhiteIp {}, ipList:{} error.", acctoken, userIp, ipList, e);
            }
        }
        return false;
    }

}
