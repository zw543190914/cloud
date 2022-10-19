package com.zw.cloud.tools.aop;

import com.zw.cloud.tools.annotation.Ide;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Objects;

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
        String lockKey;
        if (arg instanceof Byte || arg instanceof Short || arg instanceof Integer || arg instanceof Long
                || arg instanceof Double || arg instanceof Float || arg instanceof Boolean || arg instanceof Character
                || arg instanceof String || arg instanceof BigDecimal) {
            // 基本类型 + String类型直接取 指定参数值 作为 锁标志
            lockKey = perFix + "_" + arg;
        } else {
            // 其他类型 获取参数对应字段
            String fieldName = ide.objectFieldName();
            if (StringUtils.isBlank(fieldName)) {
                throw new RuntimeException("加锁对象字段名称为空");
            }
            try {
                Field declaredField = arg.getClass().getDeclaredField(fieldName);
                declaredField.setAccessible(true);
                Object fieldValue = declaredField.get(arg);
                if (Objects.isNull(fieldValue)) {
                    throw new RuntimeException("加锁对象字段值为空");
                }
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

