package com.zw.cloud.tools.concurrent.utils;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) throws Exception{
        //倒时 计数器 计时器为0时，等待线程开始启动，其他线程也并行。否则等待线程一直等待，计数器不可以重置。 阻塞主线程
        CountDownLatch countDownLatch = new CountDownLatch(7);
        //循环计数
        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " done");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        // 会阻塞主线程
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " done");
    }
}
