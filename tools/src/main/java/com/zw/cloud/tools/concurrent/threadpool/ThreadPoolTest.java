package com.zw.cloud.tools.concurrent.threadpool;

import java.util.concurrent.*;
import java.util.stream.Stream;

public class ThreadPoolTest {

    /**
     * ThreadPoolExecutor.AbortPolicy() 抛出异常
     * ThreadPoolExecutor.CallerRunsPolicy() 由调用线程执行
     * ThreadPoolExecutor.DiscardPolicy() 丢弃任务 不会抛出异常
     * ThreadPoolExecutor.DiscardOldestPolicy() 尝试和最早的竞争，竞争失败丢弃任务 不会抛出异常
     */
    public static void main(String[] args) {
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                4,
                6,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                threadFactory,
                new ThreadPoolExecutor.DiscardOldestPolicy());

        // 最大承载 最大核心线程数+队列数量

        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " OK");
            });
        }

        /**
         * 获取活跃线程
         */
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        int activeCount = mainThreadGroup.activeCount();
        Thread[] threads = new Thread[activeCount];
        mainThreadGroup.enumerate(threads);
        Stream.of(threads).filter(Thread::isAlive).forEach(System.out::println);

        threadPoolExecutor.shutdown();
    }

    public static void executor() {

        /**
         * 0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,
         * new SynchronousQueue<Runnable>()
         */
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        /**
         * corePoolSize, Integer.MAX_VALUE,
         *               DEFAULT_KEEPALIVE_MILLIS, MILLISECONDS,
         *               new DelayedWorkQueue()
         */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        /**
         * nThreads, nThreads,0L, TimeUnit.MILLISECONDS,
         * new LinkedBlockingQueue<Runnable>(),
         * threadFactory
         */
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);

        /**
         * 1, 1,0L, TimeUnit.MILLISECONDS,
         * new LinkedBlockingQueue<Runnable>())
         */
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    }
}
