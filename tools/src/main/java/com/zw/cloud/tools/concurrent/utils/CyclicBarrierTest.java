package com.zw.cloud.tools.concurrent.utils;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {

        // 不会阻塞主线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> System.out.println("all done"));
        //收集龙珠
        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " run");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            },String.valueOf(i)).start();
        }
    }
}
