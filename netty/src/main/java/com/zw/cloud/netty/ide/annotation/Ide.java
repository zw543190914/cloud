package com.zw.cloud.netty.ide.annotation;


import com.zw.cloud.netty.ide.enums.IdeTypeEnum;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ide {


    /**
     * 自定义key的前缀用来区分业务
     */
    String perFix() default "default:";

    /**
     * 重复提交的时间间隔 单位为秒
     */
    int timeOut() default 10;

    /**
     * 禁止重复提交的模式
     */
    IdeTypeEnum ideTypeEnum() default IdeTypeEnum.PARAMS;
}

