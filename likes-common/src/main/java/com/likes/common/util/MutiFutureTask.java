package com.likes.common.util;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.*;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author xiaoming
 * @ClassName 带返回结果的批量线程执行工具
 * @Description
 * @Date 2:17 下午 8/11/20
 **/
public class MutiFutureTask<T, V> {
    private static final int PoolSize = 30;
    /**
     * 线程池
     */
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(PoolSize, 50, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000), new ThreadPoolExecutor.CallerRunsPolicy());
    /**
     * 带有回调机制的线程池
     */
    public static ListeningExecutorService service = MoreExecutors.listeningDecorator(threadPoolExecutor);

    public static <T, V> List<V> batchExec(List<T> params, BatchFuture<T, V> batchFuture)
            throws ExecutionException, InterruptedException {
        if (CollectionUtils.isEmpty(params)) {
            return null;
        }
        final List<V> value = new CopyOnWriteArrayList<V>();
        List<ListenableFuture<V>> futures = new CopyOnWriteArrayList<>();
        for (T t : params) {
            //将实现了Callable的任务提交到线程池中，得到一个带有回调机制的ListenableFuture实例
            ListenableFuture<V> sfuture = service.submit(new SingleTask<T, V>(t, batchFuture));
            Futures.addCallback(sfuture, new FutureCallback<V>() {
                @Override
                public void onSuccess(V result) {
                    value.add(result);
                }

                @Override
                public void onFailure(Throwable t) {
                    throw new RuntimeException(t);
                }
            }, service);
            futures.add(sfuture);
        }
        ListenableFuture<List<V>> allAsList = Futures.allAsList(futures);
        allAsList.get();

        return value;
    }

    /**
     * 业务实现类
     *
     * @param <T>
     * @param <V>
     */

    private static class SingleTask<T, V> implements Callable<V> {
        private final T param;
        private final BatchFuture<T, V> batchFuture;

        public SingleTask(T param, BatchFuture<T, V> batchFuture) {
            this.param = param;
            this.batchFuture = batchFuture;
        }

        @Override
        public V call() throws Exception {
            return batchFuture.callback(param);
        }
    }

    public interface BatchFuture<T, V> {
        V callback(T param);
    }

    public static void main(String[] args) {
        ArrayList<String> strings = Lists.newArrayList("1", "2", "3");


        try {
            List<HashMap<String, String>> resultMap = MutiFutureTask.batchExec(strings, (String str) -> {
                HashMap<String, String> objectObjectHashMap = Maps.newHashMap();
                objectObjectHashMap.put(str, str);
                return objectObjectHashMap;
            });
            resultMap.stream().filter(x -> x != null).forEach(e -> {
                System.out.println(e);
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
