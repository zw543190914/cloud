package com.zw.cloud.logging.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class MDCInterceptor implements HandlerInterceptor {

    private static final String TRACE_ID = "TRACE_ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TID
        String tid ;
        //可以考虑让客户端传入链路ID，但需保证一定的复杂度唯一性；如果没使用默认UUID自动生成
        if (StringUtils.isNotBlank(request.getHeader(TRACE_ID))){
            tid = request.getHeader(TRACE_ID);
        } else {
            tid = UUID.randomUUID().toString().replace("-", "");
        }
        MDC.put(TRACE_ID, tid);
        log.info("[MDCInterceptor][preHandle] tid is {}",tid);
        //回写requestId到response中
        response.setHeader(TRACE_ID,tid);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(TRACE_ID);
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
