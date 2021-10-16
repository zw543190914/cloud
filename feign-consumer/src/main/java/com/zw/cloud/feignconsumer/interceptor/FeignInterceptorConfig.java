package com.zw.cloud.feignconsumer.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class FeignInterceptorConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String access_token = request.getHeader("ACCESS_TOKEN");
            System.out.println(access_token);
            requestTemplate.header("ACCESS_TOKEN", access_token);
        }
    }

}

