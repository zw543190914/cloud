package com.zw.cloud.netty.ide.aop;

import com.zw.cloud.netty.ide.annotation.IdeLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class IdeLockAspect {

    @Autowired
    private RedissonClient redissonClient;


    @Around("@annotation(ide)")
    public Object process(ProceedingJoinPoint joinPoint, IdeLock ide) throws Throwable{
        Object[] args = joinPoint.getArgs();
        int argsIndex = ide.paramIndex();
        if (argsIndex >= args.length) {
            throw new RuntimeException("加锁时ide.paramIndex错误");
        }
        Object arg = args[argsIndex];

        String perFix = ide.perFix();
        StringBuilder lockKey = new StringBuilder(perFix);
        if (arg instanceof Byte || arg instanceof Short || arg instanceof Integer || arg instanceof Long
                || arg instanceof Double || arg instanceof Float || arg instanceof Boolean || arg instanceof Character
                || arg instanceof String || arg instanceof BigDecimal) {
            // 基本类型 + String类型直接取 指定参数值 作为 锁标志
            lockKey.append("_").append(arg);
        } else {
            // 其他类型 获取参数对应字段
            String[] fieldNames = ide.objectFieldName();

            try {
                for (String fieldName : fieldNames) {
                    if (StringUtils.isBlank(fieldName)) {
                        throw new RuntimeException("加锁对象字段名称为空");
                    }
                    Field declaredField = arg.getClass().getDeclaredField(fieldName);
                    declaredField.setAccessible(true);
                    Object fieldValue = declaredField.get(arg);
                    if (Objects.isNull(fieldValue)) {
                        throw new RuntimeException("加锁对象字段值为空,字段名称为" + fieldName);
                    }
                    lockKey.append("_").append(fieldValue);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("加锁时ide.objectFieldName错误");
            }
        }
        log.info("[IdeLockAspect][process] lockKey is {}",lockKey.toString());
        RLock lock = redissonClient.getLock(lockKey.toString());
        if (ide.useTryLock()) {
            boolean tryLock;
            try {
                tryLock = lock.tryLock(0, ide.timeOutSecond(), TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                log.warn("[IdeLockAspect][process] lockKey is {},tryLock error is ",lockKey,e);
                throw e;
            }
            if (!tryLock) {
                throw new RuntimeException("当前有其他人正在操作,获取锁失败,请稍后重试");
            }
        } else {
            lock.lock(ide.timeOutSecond(), TimeUnit.SECONDS);
        }
        log.info("[IdeLockAspect][process] lockKey is {},获取锁成功",lockKey.toString());
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            log.warn("[IdeLockAspect][process] throwable is ",throwable);
            throw throwable;
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                log.info("[IdeLockAspect][process] lockKey is {},主动释放锁成功",lockKey.toString());
                lock.unlock();
            }
        }
    }



    public static void main(String[] args) {
        // java.lang.String
        System.out.println(String.class.getName());
        // java.lang.String
        System.out.println(String.class.getTypeName());

    }
}

