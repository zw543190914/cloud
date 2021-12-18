package com.zw.cloud.tools.threadlocal;

public class RequestHolder {

    private final static ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void setValue (String str) {
        THREAD_LOCAL.set(str);
    }
    //获取线程中保存的值
    public static String getValue() {
        return THREAD_LOCAL.get();
    }

    //移除线程中保存的值
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
