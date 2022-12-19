package com.likes.common.mq.producer.brokery;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MessageHolderAsyncQueue {

    protected static Logger logger = LoggerFactory.getLogger(AsyncBaseQueue.class);

    private static final int THREAD_SIZE = Runtime.getRuntime().availableProcessors();

    private static final int QUEUE_SIZE = 10000;

    private static final ExecutorService senderHolderAsync = new ThreadPoolExecutor(THREAD_SIZE, THREAD_SIZE,
            60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(QUEUE_SIZE),
            r -> {
                Thread t = new Thread(r);
                t.setName("rabbitmq_client_async_sender");
                return t;
            },
            (r, executor) -> logger.error("async sender is error rejected, runnable:{}, executor:{}", r, executor)
    );

    public static void submit(Runnable runnable) {
        senderHolderAsync.submit(runnable);
    }
}
