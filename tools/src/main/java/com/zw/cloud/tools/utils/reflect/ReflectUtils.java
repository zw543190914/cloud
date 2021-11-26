package com.zw.cloud.tools.utils.reflect;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.JSONUtil;
import com.zw.cloud.tools.entity.User;
import com.zw.cloud.tools.utils.html.JsoupUtils;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

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

        System.out.println("====================");
        Method[] declaredMethods = userClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            declaredMethod.setAccessible(true);
            System.out.println("method:" + declaredMethod.getName());
        }
    }
}
