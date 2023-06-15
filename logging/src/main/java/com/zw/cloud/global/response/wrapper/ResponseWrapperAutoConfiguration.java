package com.zw.cloud.global.response.wrapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@ConditionalOnWebApplication
public class ResponseWrapperAutoConfiguration {

    @Configuration
    @Import(ResponseAutoWrapper.class)
    public static class ResponseAutoWrapperHandlersConfiguration {
        static {
            // 使用 static 提前初始化统一异常处理类
            log.info("[ResponseWrapperAutoConfiguration] Import response wrapper for rest app");
        }
    }
}
