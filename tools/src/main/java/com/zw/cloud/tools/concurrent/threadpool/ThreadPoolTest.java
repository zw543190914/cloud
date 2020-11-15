package com.zw.cloud.tools.concurrent.threadpool;

import java.util.concurrent.*;

public class ThreadPoolTest {

    /**
     * ThreadPoolExecutor.AbortPolicy() 抛出异常
     * ThreadPoolExecutor.CallerRunsPolicy() 由调用线程执行
     * ThreadPoolExecutor.DiscardPolicy() 丢弃任务 不会抛出异常
     * ThreadPoolExecutor.DiscardOldestPolicy() 尝试和最早的竞争，竞争失败丢弃任务 不会抛出异常
     */
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                4,
                6,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        // 最大承载 最大核心线程数+队列数量
        try {
            for (int i = 0; i < 10; i++) {
                threadPoolExecutor.submit(()->{
                    System.out.println(Thread.currentThread().getName() +" OK");
                });
            }
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
