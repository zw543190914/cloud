package com.zw.cloud.netty.web.interceptor;

import com.zw.cloud.common.exception.BizException;
import com.zw.cloud.common.utils.JjwtUtils;
import com.zw.cloud.netty.utils.UrlParamsUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class HttpInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        log.info("[HttpInterceptor][preHandle]uri is {},method is {}",uri,method);
        String accessToken = request.getHeader("accessToken");
        if (StringUtils.isBlank(accessToken)) {
            Map<String, String> urlParams = UrlParamsUtils.getUrlParams(uri);
            accessToken = urlParams.get("accessToken");
            if (StringUtils.isBlank(accessToken)) {
                throw new BizException("请先登录");
            }
        }
        try {
            // 过期会抛出异常 ExpiredJwtException
            JjwtUtils.parseJwt(accessToken);
        } catch (Exception e) {
            throw new BizException("token已过期,请重新登录");
        }
        /*
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

    }




}
