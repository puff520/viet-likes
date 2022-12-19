package com.likes.common;

import com.alibaba.fastjson.JSONObject;
import com.likes.common.constant.Constants;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.PageResult;
import com.likes.common.model.common.RequestInfo;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.util.BaseUtil;
import com.likes.common.util.ExceptionAdviceTool;
import com.likes.common.util.SourceUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author abu
 * @version 1.0
 * @Description
 * @revise
 * @time 2018年1月5日 下午2:40:30
 */
@Controller
public class BaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    protected static final String REDIRECT_PREFIX = "redirect:";
    protected static final String SLIDE_VALIDATE_SECRET = "haizuishuai!!!";
    private final boolean logParameter = true;

    @Resource
    protected HttpServletRequest request;

    /**
     * @return
     * @author abu
     * <p>
     * Description:<br>
     * APP用户登录
     */
    public LoginUser getLoginUserAPP() {
        return (LoginUser) request.getSession().getAttribute(Constants.APP_LOGIN_INFO);
    }

    /**
     * @return
     * @author abu
     * <p>
     * Description:<br>
     * 运营后台登录
     */
    public LoginUser getLoginAdmin() {
        return (LoginUser) request.getSession().getAttribute(Constants.ADMIN_LOGIN_INFO);
    }


    public LoginUser getLoginAgent() {
        return (LoginUser) request.getSession().getAttribute(Constants.APP_LOGIN_AGENT_INFO);
    }


    /**
     * 用流介绍参数
     *
     * @param <V>
     * @param req
     * @param valueClass
     * @return
     */
    public <V> V parseMsg(HttpServletRequest req, Class<V> valueClass) {
        try {
            String body = readReqMsg(req);
            if (body == null || body.isEmpty()) {
                return null;
            }
            // body = AESUtils.decryptData(body,AESUtils.KEY);
            req.getSession().setAttribute("requestbody", body);
            if (body == null || body.isEmpty()) {
                return null;
            }
            // return JSON.parseObject(text, clazz)
            return jsonToObject(body, valueClass, false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private String readReqMsg(HttpServletRequest request) {
        StringBuffer reqMsg = new StringBuffer();
        BufferedReader reader;
        try {
            reader = request.getReader();
            String str = "";
            while ((str = reader.readLine()) != null) {
                reqMsg.append(str);
            }

            return reqMsg.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <V> V jsonToObject(String data, Class<V> valueClass, boolean fullmap) {
        if (data == null) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (!fullmap) {
                objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            }
            return objectMapper.readValue(data, valueClass);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // 获取客户端IP
    public String getIpAddress() {
        return BaseUtil.getIpAddress(request);
    }

    /**
     * 获取服务器地址
     *
     * @return Ip地址
     */
    public String getServerIp() {
        // 获取操作系统类型
        String sysType = System.getProperties().getProperty("os.name");
        String ip;
        if (sysType.toLowerCase().startsWith("win")) { // 如果是Windows系统，获取本地IP地址
            String localIP = null;
            try {
                localIP = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {

            }
            if (localIP != null) {
                return localIP;
            }
        } else {
            ip = getIpByEthNum("eth0"); // 兼容Linux
            if (ip != null) {
                return ip;
            }
        }
        return "获取服务器IP错误";
    }

    /**
     * 根据网络接口获取IP地址
     *
     * @param ethNum 网络接口名，Linux下是eth0
     * @return
     */
    private String getIpByEthNum(String ethNum) {
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

        }
        return "获取服务器IP错误";
    }

    /**
     * 统一log参数
     *
     * @param prefix      日志前缀，一般为方法名或任意自定义能唯一标识的前缀
     * @param requestInfo
     * @return
     */
    protected String logRequestParams(String prefix, RequestInfo requestInfo) {
        if (isRequestInfoInValid(requestInfo)) {
            return null;
        }
        String params = JSONObject.toJSONString(requestInfo.getData());
        logger.info("{} params:{}", prefix, params);
        return params;
    }

    /**
     * 校验请求参数是否可用
     *
     * @param requestInfo
     * @return
     */
    protected boolean isRequestInfoInValid(RequestInfo requestInfo) {
        return null == requestInfo || null == requestInfo.getData();
    }

    /**
     * 重定向
     *
     * @param url
     * @return
     */
    protected String redirectTo(String url) {
        if (StringUtils.isNotEmpty(url)) {
            return REDIRECT_PREFIX + StringUtils.trim(url);
        }
        return null;
    }



    /**
     * 解析获取前端传入的 userIP
     *
     * @param request
     * @return
     */
    protected String resolveUserIP(HttpServletRequest request) {
        String userIp = request.getHeader(Constants.HEADER_USER_IP);
        if (StringUtils.isNotEmpty(userIp)) {
            return userIp;
        }
        userIp = request.getParameter(Constants.HEADER_USER_IP);
        if (StringUtils.isNotEmpty(userIp)) {
            return userIp;
        }
        return "";
    }

    /**
     * 获取前端参数 userIP，为空则通过后台获取请求IP
     *
     * @param request
     * @return
     */
    protected String resolveIp(HttpServletRequest request) {
        String userIp = resolveUserIP(request);
        if (StringUtils.isNotEmpty(userIp)) {
            return userIp;
        }
        return BaseUtil.getIpAddress(request);
    }

    /**
     * 获取前端参数 clientType
     *
     * @param request
     * @return
     */
    protected String resolveClientType(HttpServletRequest request) {
        String clientType = request.getHeader(Constants.CLIENT_TYPE_STRING);
        if (StringUtils.isEmpty(clientType)) {
            clientType = SourceUtil.getClientSource(request);
        }
        return StringUtils.isEmpty(clientType) ? "" : clientType.toUpperCase();
    }

    /**
     * 获取手机型号
     * @param request
     * @return
     */
    protected String resolveClientPhoneModel(HttpServletRequest request) {
        return request.getHeader(Constants.CLIENT_PHONE_MODEL);
    }

    //解析一些map请求参数里的常用参数

    /**
     * 解析获取参数 id
     *
     * @param map
     * @return
     */
    protected int resolveId(Map<String, Object> map) {
        Object id = map.get("id");
        if (null == id) {
            return 0;
        }
        Integer idNum = Integer.valueOf(id.toString());
        return null == idNum || idNum < 0 ? 0 : idNum;
    }

    /**
     * 解析获取参数 pageNo
     *
     * @param map
     * @return
     */
    protected int resolvePageNnm(Map<String, Object> map) {
        Object pageNo = map.get("pageNo");
        if (null == pageNo) {
            return PageResult.DEFAULT_PAGE_NO;
        }
        Integer num = Integer.valueOf(pageNo.toString());
        return null == num || num <= 0 ? PageResult.DEFAULT_PAGE_NO : num;
    }

    /**
     * 解析获取参数 pageSize
     *
     * @param map
     * @return
     */
    protected int resolvePageSize(Map<String, Object> map) {
        Object pageSize = map.get("pageSize");
        if (null == pageSize) {
            return PageResult.DEFAULT_PAGE_NO;
        }
        Integer size = Integer.valueOf(pageSize.toString());
        return null == size || size <= 0 ? PageResult.DEFAULT_PAGE_SIZE : size;
    }

    /**
     * 解析获取参数 name
     *
     * @param map
     * @param defaultValue 是否返回默认值 默认值: ""
     * @return
     */
    protected String resolveName(Map<String, Object> map, boolean defaultValue) {
        String name = (String) map.get("name");
        if (null == name) {
            return defaultValue ? "" : null;
        }
        return name.trim();
    }

    /**
     * 返回错误 ResultInfo
     *
     * @param e
     * @param msg
     * @return
     */
    protected ResultInfo getErrorResult(Exception e, String msg) {
        return ResultInfo.error(ExceptionAdviceTool.getServerStartupException(e, msg));
    }

    protected void write(HttpServletResponse response, String content, String contentType) throws IOException {
        if (StringUtils.isNotEmpty(contentType)) {
            response.setContentType(contentType);
        }
        PrintWriter out = response.getWriter();
        out.write(content);
        out.close();
    }


    protected void toLogParameters(String prefix, HttpServletRequest request) {
        BaseUtil.logParameters(request, prefix);
        logHeaders(request);
    }

    protected void logHeaders(HttpServletRequest request) {
        if (!logParameter) {
            return;
        }
        toLogHeaders(request);
    }

    protected void toLogHeaders(HttpServletRequest request) {
        BaseUtil.logHeaders(request);
    }

}
