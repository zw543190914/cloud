package com.zw.cloud.tools.concurrent.single;

//饿汉式
public class Hungry {
    // 类初始化时,会立即加载该对象，线程天生安全,调用效率高
    private static final Hungry hungry = new Hungry();
    private Hungry() {
        System.out.println("hungry初始化");
    }

    public static Hungry getInstance() {
        return hungry;
    }
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                Hungry instance = getInstance();
                System.out.println(Thread.currentThread().getName() + instance);
            }).start();
        }
    }
}
