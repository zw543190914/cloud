package com.zw.cloud.tools.filter;

import com.zw.cloud.tools.threadlocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class HttpFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //打印路径和线程id日志
        log.info("[HttpFilter][doFilter]{},{}",request.getRequestURI(),Thread.currentThread().getId());

        //将线程Id放在自定义的RequestHolder
        RequestHolder.setValue(String.valueOf(Thread.currentThread().getId()));

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }

}