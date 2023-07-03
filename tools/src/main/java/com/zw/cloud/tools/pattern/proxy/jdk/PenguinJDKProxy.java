package com.zw.cloud.tools.pattern.proxy.jdk;

import com.alibaba.fastjson2.JSON;
import com.zw.cloud.tools.pattern.proxy.PenguinService;
import com.zw.cloud.tools.pattern.proxy.PenguinServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

@Slf4j
public class PenguinJDKProxy implements InvocationHandler {

    /**
     * 原始对象,被代理对象
     */
    private Object originalObj;

    public PenguinJDKProxy(Object originalObj) {
        this.originalObj = originalObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("[PenguinJDKProxy][invoke] before");
        Object result = method.invoke(originalObj, args);
        log.info("[PenguinJDKProxy][invoke] after,result is {}", JSON.toJSONString(result));
        return result;
    }

    public static void main(String[] args) {
        PenguinService penguinService = new PenguinServiceImpl();
        PenguinJDKProxy jdkProxy = new PenguinJDKProxy(penguinService);
        ClassLoader classLoader = penguinService.getClass().getClassLoader();
        PenguinService proxyPenguinService = (PenguinService)Proxy.newProxyInstance(classLoader, new Class[]{PenguinService.class}, jdkProxy);
        proxyPenguinService.beat();
    }
}
