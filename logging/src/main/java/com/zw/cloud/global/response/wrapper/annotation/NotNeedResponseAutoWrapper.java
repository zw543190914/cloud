package com.zw.cloud.global.response.wrapper.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNeedResponseAutoWrapper {
}
