package com.zw.cloud.netty.web.config;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import com.zw.cloud.netty.web.interceptor.HttpInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    HttpInterceptor httpInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器到 springMVC 机制， 然后它会返回一个拦截器注册
        InterceptorRegistration regist =  registry.addInterceptor(httpInterceptor);
        // 指定拦截匹配模式，限制拦截器拦截请求
        regist.addPathPatterns("/**");
        // 不拦截的路径
        regist.excludePathPatterns(
                "/chat/user/registerOrLogin","/chat/user/uploadFace","/chat/user/mockUser",
                "/html/selenium/**",
                "/exclude/*","/images/*","/error","/favicon.ico");
    }
}

