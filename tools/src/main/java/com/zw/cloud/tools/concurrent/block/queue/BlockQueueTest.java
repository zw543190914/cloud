package com.zw.cloud.tools.concurrent.block.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class BlockQueueTest {

    public static void main(String[] args) throws Exception{
        //test1();
        //test2();
        test3();
        //test4();
    }

    // 抛出异常 add remove 查看队首元素 element
    public static void test1(){
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println( blockingQueue.add("A"));
        System.out.println( blockingQueue.add("B"));
        System.out.println( blockingQueue.add("C"));
        //System.out.println( blockingQueue.add("D"));
        System.out.println("================");
        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());
    }

    // 不抛出异常 offer poll 查看队首元素 peek
    public static void test2(){
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println( blockingQueue.offer("A"));
        System.out.println( blockingQueue.offer("B"));
        System.out.println( blockingQueue.offer("C"));
        System.out.println( blockingQueue.offer("D"));
        System.out.println("================");
        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    // 等待 有时间阻塞 offer poll
    public static void test3()throws Exception{
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println( blockingQueue.offer("A", 1,TimeUnit.SECONDS));
        System.out.println( blockingQueue.offer("B", 1,TimeUnit.SECONDS));
        System.out.println( blockingQueue.offer("C", 1,TimeUnit.SECONDS));
        System.out.println( blockingQueue.offer("D", 1,TimeUnit.SECONDS));
        System.out.println("================");
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
    }

    // 等待 一直阻塞 put take
    public static void test4()throws Exception{
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("A");
        blockingQueue.put("B");
        blockingQueue.put("C");
        //blockingQueue.put("D");
        System.out.println("================");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        //System.out.println(blockingQueue.take());
    }
}
