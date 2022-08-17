package com.zw.cloud.feignprovider02.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class MvcInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String platSource = request.getHeader("plat_source");
        log.info("[MvcInterceptor][preHandle] platSource is {}", platSource);
        return true;
    }

}
