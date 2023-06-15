package com.zw.cloud.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@ConditionalOnWebApplication
public class GlobalExceptionHandlerAutoConfiguration {
    @Configuration
    @Import(GlobalExceptionHandler.class)
    public static class RestAppExceptionHandlersConfiguration {
        static {
            // 使用 static 提前初始化统一异常处理类
            log.info("[GlobalExceptionHandlerAutoConfiguration] Import exception handler for rest app");
        }
    }
}
