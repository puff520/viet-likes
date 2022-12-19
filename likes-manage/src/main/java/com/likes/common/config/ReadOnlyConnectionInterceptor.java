//package com.likes.common.config;
//
//import lombok.extern.log4j.Log4j2;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.core.Ordered;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//@Log4j2
//public class ReadOnlyConnectionInterceptor implements Ordered {
//
//    @Around(value = "@annotation(com.likes.common.annotation.ReadOnlyConnection)")
//    public Object proceed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        try {
//            DbContextHolder.setDbType(DbContextHolder.DbType.SLAVE);
//            Object result = proceedingJoinPoint.proceed();
//            return result;
//        } finally {
//            DbContextHolder.clearDbType();
//        }
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
