package com.likes.common.util;

import com.likes.common.enums.StatusCode;
import com.likes.common.exception.BusinessException;
import com.likes.common.model.HystrixBadRequestException;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

/**
 * @Author: admin
 * @Description:
 * @Version: 1.0.0
 * @Date; 2018/5/25 025 17:51
 */
public class ExceptionAdviceTool {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdviceTool.class);

    public static ResultInfo<String> getExceptionAdviceResultInfo(Exception exception, HttpServletRequest request) {
        LoginUser loginUser = BaseUtil.getLoginUserAPP(request);
        String accno = null == loginUser ? null : loginUser.getAccno();
        logger.error("{}:{} Global Exception:{} params:{}; headers:{}", accno, request.getRequestURI(), exception.getMessage(), BaseUtil.getRequestParameters(request), BaseUtil.getHeaders(request), exception);

        ResultInfo<String> resultInfo = ResultInfo.error();
        try {
            if (exception instanceof BindException) {
                BindException bindException = (BindException) exception;
                resultInfo.setInfo(bindException.getBindingResult().getFieldError().getDefaultMessage());
            } else if (exception instanceof BusinessException) {
                BusinessException businessException = (BusinessException) exception;
                resultInfo.setInfo(businessException.getMessage());
                resultInfo.setStatus(businessException.getCode());
            } else if (exception instanceof MissingServletRequestParameterException) {
                resultInfo.setStatus(StatusCode.PARAM_ERROR.getCode());
                resultInfo.setInfo(StatusCode.PARAM_ERROR.getValue());
            } else if (exception instanceof HystrixBadRequestException) {
                resultInfo.valueOf(StatusCode.HYSTRIX_BAD_REQUEST);
            } else if (exception instanceof AccessDeniedException) {
                resultInfo.valueOf(StatusCode.ACCESS_DENIED);
            } else {
                resultInfo.setInfo(getServerStartupException(exception, StatusCode.UNKNOW_ERROR.getValue()));
            }
        } catch (Exception e) {
            logger.error("ExceptionAdviceTool global exception occur error", e);
        }
        return resultInfo;
    }

    public static String getServerStartupException(Exception e, String defaultMsg) {
        String errorMessage = e.getCause().getMessage();
        errorMessage = null == errorMessage ? "" : errorMessage;
        if (errorMessage.contains("Load balancer does not have available server for client")) {
            return "服务启动中，请稍候再试！";
        }
        return defaultMsg;
    }

}
