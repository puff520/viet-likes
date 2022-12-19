package com.likes;

import com.likes.common.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.TimeZone;


@SpringBootApplication(scanBasePackages = {"com.likes"})
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
@MapperScan({"com.likes.common.mybatis"})
@EnableAsync
@EnableTransactionManagement
@Import(cn.hutool.extra.spring.SpringUtil.class)
public class LikesApp extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LikesApp.class);
    }

    public static void main(String[] args) {
//        TimeZone.setDefault(TimeZone.getTimeZone("GMT-5"));
        ApplicationContext applicationContext = SpringApplication.run(LikesApp.class, args);
        SpringUtil.setApplicationContext(applicationContext);
    }

}
