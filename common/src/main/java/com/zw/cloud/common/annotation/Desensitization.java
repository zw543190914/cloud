package com.zw.cloud.common.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zw.cloud.common.enums.DesensitizationTypeEnum;
import com.zw.cloud.common.config.desensitization.serialize.DesensitizationSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Retention(RetentionPolicy.RUNTIME)：运行时生效。
 * @Target(ElementType.FIELD)：可用在字段上。
 * @JacksonAnnotationsInside：此注解可以点进去看一下是一个元注解，主要是用户打包其他注解一起使用。
 * @JsonSerialize：上面说到过，该注解的作用就是可自定义序列化，可以用在注解上，方法上，字段上，类上，运行时生效等等，根据提供的序列化类里面的重写方法实现自定义序列化。
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = DesensitizationSerialize.class)
public @interface Desensitization {
    /**
     * 脱敏数据类型，在MY_RULE的时候，startInclude和endExclude生效
     */
    DesensitizationTypeEnum type() default DesensitizationTypeEnum.MY_RULE;

    /**
     * 脱敏开始位置（包含）
     */
    int startInclude() default 0;

    /**
     * 脱敏结束位置（不包含）
     */
    int endExclude() default 0;
}