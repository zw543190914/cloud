package com.zw.cloud.tools.base;

import org.springframework.stereotype.Component;

@Component
public class ThreadContext {

    private static ThreadLocal<String> workIdThreadLocal = ThreadLocal.withInitial(() -> "");
    private static ThreadLocal<String> workNameThreadLocal = ThreadLocal.withInitial(() -> "");

    public ThreadLocal<String> getWorkNameThreadLocal() {
        return workNameThreadLocal;
    }

    public ThreadLocal<String> getWorkIdThreadLocal() {
        return workIdThreadLocal;
    }

}
