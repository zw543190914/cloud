package com.zw.cloud.common.aop;

import com.zw.cloud.common.annotation.UseTimeCount;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class UseTimeCountAop {

    @Around("@annotation(useTimeCount)")
    public Object countUseTime(ProceedingJoinPoint joinPoint, UseTimeCount useTimeCount) throws Throwable{
        Signature joinPointSignature = joinPoint.getSignature();
        String className = joinPointSignature.getDeclaringTypeName();
        String methodName = joinPointSignature.getName();
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            log.info("[UseTimeCountAop][countUseTime]class is {},methodName is {},use time is {}",
                    className,methodName,System.currentTimeMillis() - start);
            return result;
        } catch (Throwable throwable) {
            log.error("[UseTimeCountAop][countUseTime]class is {},methodName is {},use time is {},error is {}",
                    className,methodName,System.currentTimeMillis() - start,throwable);
            throw throwable;
        }
    }
}
