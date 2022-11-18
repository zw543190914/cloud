package com.zw.cloud.tools.annotation;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ide {

    // 锁前缀
    String perFix() default "tool:";
    // 超时释放时间 单位秒
    int timeOutSecond() default 3;
    // key的参数下标
    int paramIndex() default 0;
    // 如果类型是我们自定义的对象，使用对象哪个字段作为key值来源
    String[] objectFieldName() default {""};
    // 使用 lock.tryLock() 还是 lock.lock() 方式加锁
    boolean useTryLock() default true;

}

