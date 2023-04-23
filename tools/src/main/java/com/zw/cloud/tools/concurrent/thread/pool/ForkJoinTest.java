package com.zw.cloud.tools.concurrent.thread.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 10亿 累加
 * 单线程
 *  forkJoin ==  extends RecursiveTask<Long>
 *  stream
 */
public class ForkJoinTest{
    public static void main(String[] args) throws Exception{
        //test1(); //648
        test2(); //193
        //test3(); //878
    }

    public static void test1(){
        long start = System.currentTimeMillis();
        long sum = 0;
        for (long i = 1; i <= 10_0000_0000L; i++) {
            sum += i;
        }
        long useTime = System.currentTimeMillis() - start;
        System.out.println("sum=" + sum +" use time:" + useTime);
    }
    // forkJoin
    public static void test2()throws Exception{
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo forkJoinDemo = new ForkJoinDemo(1L,10_0000_0000L,100000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoinDemo);
        Long sum = submit.get();
        long useTime = System.currentTimeMillis() - start;
        System.out.println("sum=" + sum +" use time:" + useTime);
    }
    // stream
    public static void test3(){
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0,Long::sum);
        long useTime = System.currentTimeMillis() - start;
        System.out.println("sum=" + sum +" use time:" + useTime);
    }
}

/**
 *  forkJoin ==  extends RecursiveTask<Long>
 */
class ForkJoinDemo extends RecursiveTask<Long> {
    private Long start;
    private Long end;
    // 临界值
    private Long tem;

    public ForkJoinDemo(Long start, Long end, Long tem) {
        this.start = start;
        this.end = end;
        this.tem = tem;
    }

    @Override
    protected Long compute() {
        if ((end - start) < tem){
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            //System.out.println(Thread.currentThread().getName() + "forkJoin");
            return sum;
        } else {
            // 分支合并
            long middle = start + ( end - start) / 2;
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle, tem);
            ForkJoinTask<Long> fork1 = task1.fork();
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end, tem);
            ForkJoinTask<Long> fork2 = task2.fork();
            return fork1.join() + fork2.join();
        }
    }
}
