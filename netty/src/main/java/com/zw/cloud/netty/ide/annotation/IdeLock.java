package com.zw.cloud.netty.ide.annotation;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IdeLock {


    String perFix() default "tool:";

    int timeOutSecond() default 3;

    int paramIndex() default 0;

    Class objectKey() default String.class;

    String objectFieldName() default "";

    boolean useTryLock() default true;

}

