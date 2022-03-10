package com.zw.cloud.common.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SendDingTalkMsgOnException {
    String token() default "7b66a82f1620672a1f5b2229d536d41cd978fb9f949141df8b40cd3b8bc9dd54";
}
