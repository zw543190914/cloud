package com.zw.cloud.tools.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) {
        Data2 data = new Data2();

        new Thread(()-> {
            for (int i = 0; i < 30; i++) {
                try {
                    data.increase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()-> {
            for (int i = 0; i < 30; i++) {
                try {
                    data.decrease();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()-> {
            for (int i = 0; i < 30; i++) {
                try {
                    data.increase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()-> {
            for (int i = 0; i < 30; i++) {
                try {
                    data.decrease();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }

}

class Data2{

    public Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public int num = 0;

    // +1
    public void increase()throws Exception{
        lock.lock();
        try {
            while (num != 0){
                condition.await();
            }
            num ++;
            System.out.println(Thread.currentThread().getName() + "==" + num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decrease() throws Exception{
        lock.lock();
        try {
            while (num == 0){
                condition.await();
            }
            num --;
            System.out.println(Thread.currentThread().getName() + "==" + num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
