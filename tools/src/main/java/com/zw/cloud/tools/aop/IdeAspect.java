package com.zw.cloud.tools.aop;

import com.zw.cloud.tools.annotation.Ide;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Slf4j
@Aspect
@Component
public class IdeAspect {

    @Before("@annotation(ide)")
    public void doBefore(JoinPoint joinPoint,Ide ide) {
        Object[] args = joinPoint.getArgs();
        int argsIndex = ide.paramIndex();
        if (argsIndex >= args.length) {
            throw new RuntimeException("加锁时ide.paramIndex错误");
        }
        Object arg = args[argsIndex];

        String perFix = ide.perFix();
        Class clazz = ide.objectKey();
        String lockKey;
        if (StringUtils.equalsIgnoreCase(clazz.getTypeName(),String.class.getTypeName())) {
            // String类型直接取 指定参数值 作为 锁标志
            lockKey = perFix + "_" + arg;
        } else {
            // 其他类型 获取参数对应字段
            String fieldName = ide.objectFieldName();
            try {
                Field declaredField = arg.getClass().getDeclaredField(fieldName);
                declaredField.setAccessible(true);
                Object fieldValue = declaredField.get(arg);
                lockKey = perFix + "_" + fieldValue;
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("加锁时ide.objectFieldName错误");
            }
        }
        log.info("[IdeAspect][doBefore] lockKey is {}",lockKey);
    }

    public static void main(String[] args) {
        // java.lang.String
        System.out.println(String.class.getName());
        // java.lang.String
        System.out.println(String.class.getTypeName());

    }
}

