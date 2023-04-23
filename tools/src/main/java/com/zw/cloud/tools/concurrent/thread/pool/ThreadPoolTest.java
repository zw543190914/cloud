package com.zw.cloud.tools.concurrent.thread.pool;

import com.zw.cloud.tools.utils.CustomerExecutorService;

import java.util.concurrent.*;
import java.util.stream.Stream;

public class ThreadPoolTest {

    /**
     * ThreadPoolExecutor.AbortPolicy() 抛出异常
     * ThreadPoolExecutor.CallerRunsPolicy() 由调用线程执行
     * ThreadPoolExecutor.DiscardPolicy() 丢弃任务 不会抛出异常
     * ThreadPoolExecutor.DiscardOldestPolicy() 尝试和最早的竞争，竞争失败丢弃任务 不会抛出异常
     */
    public static void main(String[] args) throws Exception{
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

        CompletionService<String> completionService = new ExecutorCompletionService<>(CustomerExecutorService.pool);

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            completionService.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("task1:" + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"result");
        }
        CompletionService<String> completionService2 = new ExecutorCompletionService<>(CustomerExecutorService.pool);
        for (int i = 6; i < 10; i++) {
            int finalI = i;
            completionService2.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("task1:" + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"result");
        }
        for (int i = 0; i < 5; i++) {
            Future<String> take = completionService.take();
            take.get();
        }
        System.out.println("end1");

        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            completionService.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("task2:" + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            },"result");
        }

        for (int i = 5; i < 10; i++) {
            int finalI = i;
            completionService.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("task2:" + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"result");
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end2");

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
