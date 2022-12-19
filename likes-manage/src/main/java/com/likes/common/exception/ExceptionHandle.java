package com.likes.common.exception;

import com.likes.common.constant.Constants;
import com.likes.common.constant.ModuleConstant;
import com.likes.common.model.LoginUser;
import com.likes.common.model.common.ResultInfo;
import com.likes.common.mybatis.entity.SysErrorlog;
import com.likes.common.service.sys.SysErrorlogService;
import com.likes.common.util.BaseUtil;
import com.likes.common.util.encrypt.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 阿布 全局异常处理
 */
@ControllerAdvice
public class ExceptionHandle {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SysErrorlogService sysErrorlogService;
    @Resource
    private HttpServletRequest request;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultInfo handle(Exception e) {
        ResultInfo error = ResultInfo.error();
        try {
            if (e instanceof BusinessException) {
                BusinessException ex = (BusinessException) e;
                error.setInfo(ex.getMessage());
                error.setStatus(ex.getCode());
            }

            LoginUser loginUser = BaseUtil.getLoginUserAPP(request);
            String accno = null == loginUser ? null : loginUser.getAccno();
            logger.error("{}:{} Global Exception:{} params:{}; headers:{}", accno, request.getRequestURI(), e.getMessage(), BaseUtil.getRequestParameters(request), BaseUtil.getHeaders(request), e);
            SysErrorlog err = new SysErrorlog();
            err.setSystemname(ModuleConstant.LIVE_MANAGE);
            err.setModelname(request.getRequestURI());
            err.setOptcontent(e.getMessage().trim());
            err.setOrginfo(MD5.md5(err.getSystemname() + err.getModelname() + err.getOptcontent()));
            err.setOptip(BaseUtil.getIpAddress(request));
            err.setLevel(Constants.ERROR_LEVEL_ERROR);
            err.setServerip(BaseUtil.getServerIp());
            this.sysErrorlogService.insert(err);
        } catch (Exception ex) {
            logger.error("live-manage global Exception occur error", ex);
        }

        return error;
    }
}
