package com.zw.cloud.tools.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.put(temp,temp);
            },String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.get(temp);
            },String.valueOf(i)).start();
        }
    }
}
class MyCache{

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public volatile Map<Integer,Integer> cacheMap = new HashMap<>();

    public void put(Integer key,Integer value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 写入:" + key);
            cacheMap.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写入结束");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(Integer key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 读出:" + key);
            Integer s = cacheMap.get(key);
            System.out.println(Thread.currentThread().getName() + " 读出结束");
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
