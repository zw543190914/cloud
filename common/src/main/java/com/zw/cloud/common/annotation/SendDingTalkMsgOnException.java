package com.zw.cloud.common.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SendDingTalkMsgOnException {
    String token() default "505e45d4253e2a98976d15de6b98a56c8635ddcab9d8b42de029ebb462c72db6";
}
