package com.zw.cloud.tools.utils.reflect;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.annotation.ExcelProperty;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.zw.cloud.tools.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class ReflectUtils {

    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("test");
        user.setCreateTime(LocalDateTime.now());
        System.out.println(ReflectUtil.getFieldValue(user,"gmtCreate"));
        System.out.println(ReflectUtil.getFieldValue(user,"name"));
        System.out.println("parent baseId:" + ReflectUtil.getFieldValue(user,"baseId"));

        User user2 = new User();
        Class<User> userClass = User.class;
        Field name = userClass.getDeclaredField("name");
        name.setAccessible(true);
        System.out.println("name value " + name.get(user));
        System.out.println("name value " + name.get(user2));
        Class<? super User> superclass = userClass.getSuperclass();
        System.out.println("parent class :" + superclass.getTypeName());

        Field[] declaredFields = userClass.getDeclaredFields();
        Set<String> fieldSet = Lists.newArrayList(declaredFields).stream().map(Field::getName).collect(Collectors.toSet());
        System.out.println("fieldSet : " + fieldSet.toString());
        for (Field field : declaredFields) {
            if (field.getName().equals("serialVersionUID")) {
                continue;
            }
            field.setAccessible(true);
            // 获取字段上的注解
            ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
            if (Objects.nonNull(annotation)) {
                String[] value = annotation.value();
                if (value.length > 0) {
                    System.out.println("ExcelProperty:" + value[0]);
                }
            }
            System.out.println("field:" + field.getName());
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(),userClass);
            // get 方法
            Method readMethod = propertyDescriptor.getReadMethod();
            Object value = readMethod.invoke(user);
            field.set(user2,value);
            //field.set(user2,field.get(user));
            System.out.println("field:" + field.getName() + ",value:" + field.get(user));

        }
        System.out.println(JSONUtil.toJsonStr(user2));

        System.out.println("=====修改字段值=====");
        Method[] declaredMethods = userClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            declaredMethod.setAccessible(true);
            System.out.println("method:" + declaredMethod.getName());
        }

        System.out.println(JSONUtil.toJsonStr(user));
        updateFiledValue(user);
        System.out.println(JSONUtil.toJsonStr(user));
        System.out.println("=====字段必填校验=====");
        //checkFiledIsFilled(user);
    }

    public static void updateFiledValue(Object obj) throws Exception{
        // 查询
        Set<String> forbidField = Sets.newHashSet("name","age");
        Class<?> clazz = obj.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (forbidField.contains(field.getName())) {
                if (Objects.equals(field.getGenericType(),String.class)) {
                    field.set(obj,"");
                } else {
                    field.set(obj,null);
                }
            }
        }
        System.out.println(JSONUtil.toJsonStr(obj));
    }

    public static void checkFiledIsFilled(Object obj) {
        Set<String> needFillField = Sets.newHashSet("name","age");
        Class<?> clazz = obj.getClass();
        needFillField.forEach(filedName -> {
            try {
                Field declaredField = clazz.getDeclaredField(filedName);
                declaredField.setAccessible(true);
                Preconditions.checkNotNull(declaredField.get(obj), filedName + "字段为空");
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // 可能没有此字段
                log.warn("[ReflectUtils][checkFiledIsFilled] warn is ",e);
            }
        });
    }
}
