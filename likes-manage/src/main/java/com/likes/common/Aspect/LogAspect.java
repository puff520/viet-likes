package com.likes.common.Aspect;


import com.alibaba.fastjson.JSON;
import com.likes.common.annotation.Syslog;
import com.likes.common.constant.Constants;
import com.likes.common.model.LoginUser;
import com.likes.common.model.SysOperlog;
import com.likes.common.mybatis.mapper.SysOperlogMapper;
import com.likes.common.util.BaseUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

    @Resource
    private SysOperlogMapper sysOperlogMapper;

    @Pointcut("@annotation(com.likes.common.annotation.Syslog)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysOperlog sysOperlog = new com.likes.common.model.SysOperlog();
        Syslog logAnnotation = method.getAnnotation(Syslog.class);
        if (logAnnotation != null) {
            // 注解上的描述
            sysOperlog.setOperationName(logAnnotation.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysOperlog.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args);
        sysOperlog.setParams(params);
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletAttributes = (ServletRequestAttributes) requestAttributes;
        if (null == servletAttributes) {
            return;
        }
        HttpServletRequest request = servletAttributes.getRequest();
        LoginUser loginUser = (LoginUser) request.getSession().getAttribute(Constants.ADMIN_LOGIN_INFO);

        // 设置IP地址
        sysOperlog.setIp(BaseUtil.getIpAddress(request));
        // 模拟一个用户名
        sysOperlog.setAccno(loginUser.getAccno());
        sysOperlog.setAcclogin(loginUser.getAcclogin());
        sysOperlog.setCreateTime(new Date());
        // 保存系统日志
        sysOperlogMapper.insertSelective(sysOperlog);
    }


}



