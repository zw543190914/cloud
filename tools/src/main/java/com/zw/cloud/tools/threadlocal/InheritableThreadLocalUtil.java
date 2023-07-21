package com.zw.cloud.tools.threadlocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InheritableThreadLocalUtil {

    private static final InheritableThreadLocal<Map<String, String>> THREAD_LOCAL = new InheritableThreadLocal<>() {
        @Override
        protected Map<String, String> initialValue() {
            Map<String, String> map = new ConcurrentHashMap();
            return map;
        }
    };

    public static String getValue(String key) {
        return (String)((Map)THREAD_LOCAL.get()).get(key);
    }

    public static void setValue(String key, String value) {
        if (key != null && value != null) {
            ((Map)THREAD_LOCAL.get()).put(key, value);
        }
    }

    public static void clear() {
        ((Map)THREAD_LOCAL.get()).clear();
    }
    public static void main(String[] args) {
        InheritableThreadLocalUtil.setValue("key","data");
        new Thread(() -> System.out.println(InheritableThreadLocalUtil.getValue("key"))).start();

        RequestHolder.setValue("data1");
        new Thread(() -> System.out.println(RequestHolder.getValue())).start();
    }
}
