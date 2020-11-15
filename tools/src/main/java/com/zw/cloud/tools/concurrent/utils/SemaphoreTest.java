package com.zw.cloud.tools.concurrent.utils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {

    public static void main(String[] args) throws Exception{
        Semaphore semaphore = new Semaphore(3);
        //循环计数
        for (int i = 0; i < 15; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            },String.valueOf(i)).start();
        }

        System.out.println(Thread.currentThread().getName() + " done");
    }
}
