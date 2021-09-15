package com.zw.cloud.tools.ide.annotation;

import com.zw.cloud.tools.ide.enums.IdeTypeEnum;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ide {


    /**
     * 自定义key的前缀用来区分业务
     */
    String perFix() default "default:";


    /**
     * 参数中的key，根据此key的name和value判断重复
     */
    @Deprecated
    String key() default "";

    /**
     * 重复提交的时间间隔
     */
    int timeOut() default 10;

    /**
     * 重复提交的时间间隔的单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 禁止重复提交的模式
     */
    IdeTypeEnum ideTypeEnum() default IdeTypeEnum.PARAMS;
}

