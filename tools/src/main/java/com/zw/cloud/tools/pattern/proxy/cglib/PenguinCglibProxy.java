package com.zw.cloud.tools.pattern.proxy.cglib;

import com.alibaba.fastjson2.JSON;
import com.zw.cloud.tools.pattern.proxy.PenguinServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class PenguinCglibProxy implements MethodInterceptor {
    /**
     * 原始对象,被代理对象
     */
    private Object originalObj;

    public PenguinCglibProxy(Object originalObj) {
        this.originalObj = originalObj;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("[PenguinCglibProxy][intercept] before");
        Object result = methodProxy.invokeSuper(proxy, args);
        log.info("[PenguinCglibProxy][intercept] after,result is {}", JSON.toJSONString(result));
        return result;
    }

    public static void main(String[] args) {
        PenguinServiceImpl penguinService = new PenguinServiceImpl();
        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(PenguinServiceImpl.class);
        // 设置enhancer的回调对象
        enhancer.setCallback(new PenguinCglibProxy(penguinService));
        // 创建代理对象
        PenguinServiceImpl penguinServiceProxy = (PenguinServiceImpl)enhancer.create();
        penguinServiceProxy.beat();

    }
}
