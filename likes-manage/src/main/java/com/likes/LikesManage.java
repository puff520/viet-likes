package com.likes;

import com.google.common.base.Preconditions;

import com.likes.common.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = {"com.likes"})
@EnableScheduling
@MapperScan({"com.likes.common.mybatis"})
@EnableTransactionManagement
@Import(cn.hutool.extra.spring.SpringUtil.class)
public class LikesManage extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LikesManage.class);
    }


    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(LikesManage.class, args);
        SpringUtil.setApplicationContext(applicationContext);
    }

}
