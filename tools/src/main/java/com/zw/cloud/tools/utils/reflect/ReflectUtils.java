package com.zw.cloud.tools.utils.reflect;

import cn.hutool.core.util.ReflectUtil;
import com.zw.cloud.tools.entity.User;

import java.util.Date;

public class ReflectUtils {

    public static void main(String[] args) {
        User user = new User();
        user.setId(1L);
        user.setName("test");
        user.setGmtCreate(new Date());
        System.out.println(ReflectUtil.getFieldValue(user,"gmtCreate"));
        System.out.println(ReflectUtil.getFieldValue(user,"name"));
    }
}
