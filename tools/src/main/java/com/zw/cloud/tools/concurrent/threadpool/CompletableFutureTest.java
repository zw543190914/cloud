package com.zw.cloud.tools.concurrent.threadpool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws Exception{

        // 没有返回值 runAsync
        /*CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "runAsync == Void");
        });
        System.out.println(Thread.currentThread().getName());
        completableFuture.get();
        */
        // 有返回值 supplyAsync
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "supplyAsync == Integer");
            int i = 10 / 0;
            return 1024;
        });

        Integer result = completableFuture.whenComplete((t, u) -> {
            System.out.println("t == " + t);// 1024 返回结果
            System.out.println("u == " + u);// 错误信息 java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
        }).exceptionally((e) -> {
            System.out.println(e.getMessage()); // java.lang.ArithmeticException: / by zero
            return 500;
        }).get();
        System.out.println(result);
    }
}
