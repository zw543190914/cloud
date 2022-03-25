package com.zw.cloud.tools.config;

import com.zw.cloud.tools.interceptor.HttpInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器到 springMVC 机制， 然后它会返回一个拦截器注册
        InterceptorRegistration regist =  registry.addInterceptor(new HttpInterceptor());
        // 指定拦截匹配模式，限制拦截器拦截请求
        regist.addPathPatterns("/**");
        // 不拦截的路径
        regist.excludePathPatterns("/exclude/*","/images/*");
    }
}

