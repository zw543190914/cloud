package com.zw.cloud.tools.concurrent.thread.pool;

import java.util.concurrent.*;

public class SubmitAndExecuteTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                4,
                6,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        Future<?> submit = threadPoolExecutor.submit(() -> {
            System.out.println("submit");
            int i = 1 / 0;
            System.out.println("submit end");
        });
        // submit会吃掉异常，可通过Future的get方法将任务执行时的异常重新抛出。
        //submit.get();
        threadPoolExecutor.execute(() -> {
            System.out.println("execute");
            int i = 1 / 0;
            System.out.println("execute end");
        });


        threadPoolExecutor.shutdown();
    }
}
