package com.zw.cloud.logging.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
@ConditionalOnExpression("${com.zw.cloud.web.log.mdc:true}")
public class MDCAutoConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MDCInterceptor());
        WebMvcConfigurer.super.addInterceptors(registry);
        log.info("[MDCAutoConfiguration][addInterceptors] Log MDC load success");
    }
}

