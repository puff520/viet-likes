package com.likes.common.config;

import com.likes.common.constant.Constants;
import com.likes.common.enums.HttpValidateEnum;
import com.likes.common.enums.SignEnum;
import com.likes.common.exception.BusinessException;
import com.likes.common.mybatis.entity.SysParameter;
import com.likes.common.mybatis.mapperext.sys.SysParameterMapperExt;
import com.likes.common.util.BaseUtil;
import com.likes.common.util.SignatureUtil;
import com.likes.common.util.SpringUtil;
import com.likes.common.util.redis.RedisBaseUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

/**
 * @author abu http访问签名验证
 */
@Aspect
@Component
@Order(1)
public class HttpValidateAspect {

    @Pointcut(value = "@annotation(com.likes.common.annotation.HttpValidate)")
    public void HttpValidate() {
    }

    /**
     * @param joinPoint
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws IntrospectionException
     * @throws IOException
     * @author abu 微服务验签
     */
    @Before("HttpValidate()")
    public void HttpValidate(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String secretkey = request.getHeader(SignEnum.SECRETKEY.toString());
        String signtoken = request.getHeader(SignEnum.SIGNTOKEN.toString());
        if (StringUtils.isEmpty(secretkey)) {
            throw new BusinessException(HttpValidateEnum.SECRETKEY_401.getKey(), HttpValidateEnum.SECRETKEY_401.getValue());
        }
        if (StringUtils.isEmpty(signtoken)) {
            throw new BusinessException(HttpValidateEnum.NOSIGN_401.getKey(), HttpValidateEnum.NOSIGN_401.getValue());
        }
        //最大容错次数
        if (!RedisBaseUtil.exists(HttpValidateEnum.SIGN_ERR_COUNT.getKey())) {
            SysParameterMapperExt sysParameterMapperExt = (SysParameterMapperExt) SpringUtil.getBean("sysParameterMapperExt");
            SysParameter sp = sysParameterMapperExt.selectByCode(HttpValidateEnum.SIGN_ERR_COUNT.getKey());
            if (sp != null && StringUtils.isNotEmpty(sp.getSysparamvalue())) {
                Constants.HTTPSIGN_ERRCOUNT_DEFAULT = Integer.parseInt(sp.getSysparamvalue());
                RedisBaseUtil.set(HttpValidateEnum.SIGN_ERR_COUNT.getKey(), Constants.HTTPSIGN_ERRCOUNT_DEFAULT);
            }
        } else {
            Constants.HTTPSIGN_ERRCOUNT_DEFAULT = RedisBaseUtil.get(HttpValidateEnum.SIGN_ERR_COUNT.getKey());
        }
        //当前ip错误次数
        String ip = BaseUtil.getIpAddress(request);
        Integer curErrNo = RedisBaseUtil.get(HttpValidateEnum.SIGN_ERR_RECORD_PREFIX + ip);
        if (curErrNo != null && curErrNo.intValue() > Constants.HTTPSIGN_ERRCOUNT_DEFAULT.intValue()) {
            throw new BusinessException(HttpValidateEnum.TOO_MANYERR_401.getKey(), HttpValidateEnum.TOO_MANYERR_401.getValue());
        }
        if (!SignatureUtil.verify(signtoken, secretkey, request.getRequestURI())) {
            RedisBaseUtil.set(HttpValidateEnum.SIGN_ERR_RECORD_PREFIX + ip, curErrNo == null ? 1 : curErrNo + 1, 24l, TimeUnit.HOURS);
            throw new BusinessException(HttpValidateEnum.ERRSIGN_401.getKey(), HttpValidateEnum.ERRSIGN_401.getValue());
        }
        RedisBaseUtil.delete(HttpValidateEnum.SIGN_ERR_RECORD_PREFIX + ip);
    }
}
