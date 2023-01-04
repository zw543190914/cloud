package com.zw.cloud.tools.config;

import com.zw.cloud.tools.interceptor.HttpInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    HttpInterceptor httpInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器到 springMVC 机制， 然后它会返回一个拦截器注册
        registry.addInterceptor(httpInterceptor)
        // 指定拦截匹配模式，限制拦截器拦截请求
        .addPathPatterns("/**")
        // 不拦截的路径
        .excludePathPatterns("/exclude/*","/images/*");
    }
}

