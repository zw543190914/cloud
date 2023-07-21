package com.zw.cloud.common.thread.local;

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
}
