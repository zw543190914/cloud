package com.zw.cloud.tools.utils.reflect;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.zw.cloud.tools.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class ReflectUtils {

    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("test");
        user.setGmtCreate(new Date());
        System.out.println(ReflectUtil.getFieldValue(user,"gmtCreate"));
        System.out.println(ReflectUtil.getFieldValue(user,"name"));

        User user2 = new User();
        Class<User> userClass = User.class;
        Field name = userClass.getDeclaredField("name");
        name.setAccessible(true);
        System.out.println("name value " + name.get(user));
        System.out.println("name value " + name.get(user2));
        Field[] declaredFields = userClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals("serialVersionUID")) {
                continue;
            }
            field.setAccessible(true);
            System.out.println("field:" + field.getName());
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(),userClass);
            // get 方法
            Method readMethod = propertyDescriptor.getReadMethod();
            Object value = readMethod.invoke(user);
            field.set(user2,value);
            //field.set(user2,field.get(user));
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
        checkFiledIsFilled(user);
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
