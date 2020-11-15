package com.zw.cloud.tools.concurrent.single;
//懒汉式
public class LazyMan {

    private static LazyMan lazyMan;

    public static LazyMan getInstance(){
        if (lazyMan == null){
            lazyMan = new LazyMan();
        }
        return lazyMan;
    }
    private LazyMan() {
        System.out.println("LazyMan初始化");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                LazyMan instance = getInstance();
                System.out.println(Thread.currentThread().getName() + instance);
            }).start();
        }
    }
}
