package com.likes.common.config;

import com.likes.common.constant.Constants;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author 阿布 开启异步任务
 */
@Configuration
@EnableAsync // 开启对异步任务的支持
public class ThreadAsyncConfigurer implements AsyncConfigurer {
    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        threadPool.setCorePoolSize(Constants.THREAD_POOL_CORE_POOL_SIZE);
        // 设置最大线程数
        threadPool.setMaxPoolSize(Constants.THREAD_POOL_MAX_POOL_SIZE);
        // 线程池所使用的缓冲队列
        threadPool.setQueueCapacity(Constants.THREAD_POOL_QUEUE_CAPACITY);
        // 等待任务在关机时完成--表明等待所有线程执行完
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        // 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
        threadPool.setAwaitTerminationSeconds(Constants.THREAD_POOL_AWAIT_TERMINATION_SECONDS);
        // 线程名称前缀
        threadPool.setThreadNamePrefix("LiveAsync-app-");
        // 初始化线程
        threadPool.initialize();
        return threadPool;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
