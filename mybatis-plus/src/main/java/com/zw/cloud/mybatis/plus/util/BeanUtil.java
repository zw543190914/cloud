package com.zw.cloud.mybatis.plus.util;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanUtil {

    public static <S, D> void copy(final S source, final D dest) {
        if (source != null && dest != null) {
            BeanUtils.copyProperties(source, dest);
        }
    }

    public static <S, D> void copyDeeply(final S source, final D dest) {
        // TODO
    }

    public static <S, D> D copy(final S source, Class<D> destClz) {
        try {
            if (source == null) {
                return null;
            }
            D dest = destClz.getConstructor().newInstance();
            copy(source, dest);
            return dest;
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException("copy bean exception: " + e.getMessage());
        }
    }

    public static <S, D> List<D> copyList(final List<S> sources, Class<D> destClz) {
        if (sources == null || sources.isEmpty()) {
            return Collections.emptyList();
        }

        ArrayList<D> list = new ArrayList<>(sources.size());
        try {
            for (Object source : sources) {
                if (source == null) {
                    continue;
                }
                D newInstance = destClz.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(source, newInstance);
                list.add(newInstance);
            }
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException("copy list exception: " + e.getMessage());
        }

        return list;
    }

    /**
     * 将Bean转成Map
     *
     * @param bean 来源bean对象
     * @param <T> bean对象的类型
     *
     * @return 返回Map
     * @author relam
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            Map beanMap;
            if (bean instanceof Map) {
                beanMap = (Map) bean;
            } else {
                beanMap = BeanMap.create(bean);
            }
            for (Object key : beanMap.keySet()) {
                map.put(key.toString(), beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map转成Bean
     *
     * @param map 来源map
     * @param bean 待填充的bean对象
     * @param <T> bean对象类型
     *
     * @return 返回填充好的bean实例
     * @author relam
     */
    public static <T> T mapToBean(Map map, T bean) {
        if (map == null || bean == null) {
            return bean;
        }
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }
}

