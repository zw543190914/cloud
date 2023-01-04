package com.zw.cloud.tools.interceptor;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class HttpInterceptor implements HandlerInterceptor {

    private ConcurrentHashMap<String, RateLimiter> rateLimiterMap  = new ConcurrentHashMap<>();

    private static final String TRACE_ID = "TRACE_ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //log.info("[HttpInterceptor][preHandle]");
        // TID
        String tid ;
        //可以考虑让客户端传入链路ID，但需保证一定的复杂度唯一性；如果没使用默认UUID自动生成
        if (StringUtils.isNotBlank(request.getHeader(TRACE_ID))){
            tid = request.getHeader(TRACE_ID);
        } else {
            tid = UUID.randomUUID().toString().replace("-", "");
        }
        MDC.put(TRACE_ID, tid);
        log.info("[HttpInterceptor][preHandle] tid is {}",tid);

        String accessToken = request.getHeader("accessToken");
        /*if (StringUtils.isBlank(accessToken)) {
            throw new Exception("请先登录");
        }*/
        /*String uri = request.getRequestURI();
        String method = request.getMethod();
        String userIp = IpUtils.getUserIp(request);
        String rateLimiterKey = userIp + ":" + uri + ":" + method;

        if (rateLimiterMap.containsKey(rateLimiterKey)) {
            RateLimiter rateLimiter = rateLimiterMap.get(rateLimiterKey);
            if (!rateLimiter.tryAcquire()){
                log.error("[HttpInterceptor][preHandle] 非法的操作 rateLimiterKey is {}",rateLimiterKey);
                throw new RuntimeException("非法的操作");
            }
        } else {
            RateLimiter rateLimiter = RateLimiter.create(100);
            rateLimiterMap.put(rateLimiterKey, rateLimiter);
        }
*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //log.info("[HttpInterceptor][postHandle]");

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //log.info("[HttpInterceptor][afterCompletion]ThreadId:"+ RequestHolder.getValue());
        MDC.remove(TRACE_ID);
    }




}
