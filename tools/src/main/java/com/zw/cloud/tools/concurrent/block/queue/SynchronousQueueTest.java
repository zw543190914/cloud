package com.zw.cloud.tools.concurrent.block.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueTest {
    public static void main(String[] args) {
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
        new Thread(() -> {

            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                synchronousQueue.put(1);
                System.out.println(Thread.currentThread().getName() + " put 2");
                synchronousQueue.put(2);
                System.out.println(Thread.currentThread().getName() + " put 3");
                synchronousQueue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "A").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " take " + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " take " + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " take " + synchronousQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "B").start();
    }
}
