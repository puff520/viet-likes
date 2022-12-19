package com.likes;

import com.likes.common.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.TimeZone;


@SpringBootApplication(scanBasePackages = {"com.likes"})
@MapperScan({"com.likes.common.mybatis"})
@EnableAsync
public class Likes2Agent extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Likes2Agent.class);
    }

    public static   void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Likes2Agent.class, args);
        SpringUtil.setApplicationContext(applicationContext);
    }

}
