package com.zw.cloud.dubboconsumer.base;


public class ThreadContext {

    private static ThreadLocal<String> workIdThreadLocal = ThreadLocal.withInitial(() -> "");
    private static ThreadLocal<String> workNameThreadLocal = ThreadLocal.withInitial(() -> "");

    public static ThreadLocal<String> getWorkNameThreadLocal() {
        return workNameThreadLocal;
    }

    public static ThreadLocal<String> getWorkIdThreadLocal() {
        return workIdThreadLocal;
    }
}
