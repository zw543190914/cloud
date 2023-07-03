package com.zw.cloud.common.utils.bean.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CopyField {

    /**
     * 目标字段名称
     */
    String targetFieldName() default "";

}
