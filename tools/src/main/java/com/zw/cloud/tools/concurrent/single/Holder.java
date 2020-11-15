package com.zw.cloud.tools.concurrent.single;

public class Holder {
    private Holder() {
        System.out.println("初始化");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                Holder instance = getInstance();
                System.out.println(Thread.currentThread().getName() + instance);
            }).start();
        }
    }

    public static Holder getInstance(){
        return Inner.HOLDER;
    }
    public static class Inner{
        private static final Holder HOLDER = new Holder();
    }
}
