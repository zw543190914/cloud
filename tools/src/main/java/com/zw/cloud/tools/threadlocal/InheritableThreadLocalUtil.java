package com.zw.cloud.tools.threadlocal;

public class InheritableThreadLocalUtil {

    private static final InheritableThreadLocal<String> THREAD_LOCAL = new InheritableThreadLocal<>();

    //设置线程需要保存的值
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

    public static void main(String[] args) {
        InheritableThreadLocalUtil.setValue("data");
        new Thread(() -> System.out.println(InheritableThreadLocalUtil.getValue())).start();

        RequestHolder.setValue("data1");
        new Thread(() -> System.out.println(RequestHolder.getValue())).start();
    }
}
