package com.zw.cloud.common.aop;

import com.alibaba.fastjson2.JSON;
import com.zw.cloud.common.annotation.UseTimeCount;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class UseTimeCountAop {

    /**
     * around before
     * before
     *
     * afterReturning/afterThrowing
     * after
     * around after
     */
    @Around("@annotation(useTimeCount)")
    public Object countUseTime(ProceedingJoinPoint joinPoint, UseTimeCount useTimeCount) throws Throwable{
        Signature joinPointSignature = joinPoint.getSignature();
        String className = joinPointSignature.getDeclaringTypeName();
        String methodName = joinPointSignature.getName();
        log.info("[UseTimeCountAop][countUseTime]class is {},methodName is {}", className,methodName);
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

    @Before("@annotation(useTimeCount)")
    public void before(JoinPoint joinPoint, UseTimeCount useTimeCount) {
        Object[] args = joinPoint.getArgs();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        log.info("[UseTimeCountAop][before]class is {},methodName is {},args is {}",className,methodName, JSON.toJSONString(args));
    }

    @After("@annotation(useTimeCount)")
    public void after(JoinPoint joinPoint, UseTimeCount useTimeCount) {
        Object[] args = joinPoint.getArgs();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        log.info("[UseTimeCountAop][after]class is {},methodName is {},args is {}",className,methodName, JSON.toJSONString(args));
    }

    @AfterReturning("@annotation(useTimeCount)")
    public void afterReturning(JoinPoint joinPoint, UseTimeCount useTimeCount) {
        Object[] args = joinPoint.getArgs();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        log.info("[UseTimeCountAop][afterReturning]class is {},methodName is {},args is {}",className,methodName, JSON.toJSONString(args));
    }

    @AfterThrowing(value = "@annotation(useTimeCount)",throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, UseTimeCount useTimeCount,Exception ex) {
        Object[] args = joinPoint.getArgs();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        log.error("[UseTimeCountAop][afterThrowing]class is {},methodName is {},args is {},ex is ",className,methodName, JSON.toJSONString(args),ex);
    }
}
