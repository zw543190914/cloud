package com.zw.cloud.tools.use.time.bean.post.processor;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * https://mp.weixin.qq.com/s/8PaGxFjvR4O8B1E6-KX7cg
 * 监控 Bean 注入耗时
 */
@Component
@Slf4j
public class TimeCostBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Long> costMap = Maps.newConcurrentMap();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        costMap.put(beanName, System.currentTimeMillis());
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (costMap.containsKey(beanName)) {
            Long start = costMap.get(beanName);
            long cost  = System.currentTimeMillis() - start;
            if (cost > 0) {
                System.out.println("bean: " + beanName + "\ttime: " + cost);
                //log.info("[TimeCostBeanPostProcessor][postProcessAfterInitialization] beanName is {},cost time is {}",beanName,cost);
            }
        }
        return bean;
    }
}
