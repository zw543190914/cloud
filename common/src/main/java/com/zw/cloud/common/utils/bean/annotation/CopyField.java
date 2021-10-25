package com.zw.cloud.common.utils.bean.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 常用bean相关方法
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CopyField {

    /**
     * 在即将被拷贝的属性上面，设置目标属性名
     */

    String targetName() default "";

    /**
     * 在即将拷贝至改属性上面，设置源属性名
     *
     * @return
     */

    String originName() default "";

}
