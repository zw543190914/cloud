package com.zw.cloud.tools.annotation;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ide {


    String perFix() default "tool:";

    int timeOutSecond() default 3;

    int paramIndex() default 0;

    String objectFieldName() default "";

    boolean useTryLock() default true;

}

