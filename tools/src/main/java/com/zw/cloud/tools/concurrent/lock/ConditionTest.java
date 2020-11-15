package com.zw.cloud.tools.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 顺序执行 A B C
 */
public class ConditionTest {

    public static void main(String[] args) {
        Data3 data = new Data3();
        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.printA();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.printB();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.printC();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

    }

}

class Data3{

    public Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public int num = 1;
    public void printA(){
        lock.lock();
        try {
            while (num != 1){
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("A");
            num = 2;
            // 唤醒 B
            condition2.signal();
        } finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try {
            while (num != 2){
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("B");
            num = 3;
            // 唤醒 C
            condition3.signal();
        } finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            while (num != 3){
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C");
            num = 1;
            // 唤醒 A
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }

}
