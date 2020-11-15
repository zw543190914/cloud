package com.zw.cloud.tools.concurrent.single;

import java.lang.reflect.Constructor;

public class LazyMan2 {

    //private static LazyMan2 lazyMan;
    private volatile static LazyMan2 lazyMan;

    //双重检测锁 懒汉模式 DCL懒汉式
    public static LazyMan2 getInstance(){
        if (lazyMan == null){
            synchronized (LazyMan2.class){
                if (lazyMan == null){
                    lazyMan = new LazyMan2(); // 不是原子性
                    /**
                     * 1 分配内存空间
                     * 2 执行构造方法，初始化对象
                     * 3 把这个对象指向这个空间
                     *
                     * 132  A
                     *      B 可能对象未完成构造
                     */
                }
            }
        }
        return lazyMan;
    }
    private LazyMan2() {
        System.out.println("LazyMan2初始化");
    }

    public static void main(String[] args) throws Exception{
        /*for (int i = 0; i < 5; i++) {
            new Thread(()->{
                LazyMan2 instance = getInstance();
                System.out.println(Thread.currentThread().getName() + instance);
            }).start();
        }*/

        // 反射
        /*LazyMan2 instance = getInstance();
        Constructor<LazyMan2> declaredConstructor = LazyMan2.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        LazyMan2 lazyMan2 = declaredConstructor.newInstance();
        System.out.println(instance);
        System.out.println(lazyMan2);*/
    }
}
