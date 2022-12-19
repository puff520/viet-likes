package com.likes.common.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author 阿布 启动后执行 实现ApplicationRunner或者CommandLineRunner
 */
@Component
@Lazy
public class ApplicationInitListener implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationInitListener.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("ApplicationInitListener bus...");
    }
}
