package com.zw.cloud.common.aop;

import com.zw.cloud.common.annotation.SendDingTalkMsgOnException;
import com.zw.cloud.common.utils.DingTalkUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DingTalkAspect {

    @AfterThrowing(pointcut = "execution(* com.zw.cloud..*.*(..))",throwing = "ex")
    public void afterthrowing(JoinPoint joinPoint, Exception ex) {
        Signature joinPointSignature = joinPoint.getSignature();
        Class classType = joinPointSignature.getDeclaringType();
        //String className = joinPointSignature.getDeclaringTypeName();
        String methodName = joinPointSignature.getName();
        String token = "7b66a82f1620672a1f5b2229d536d41cd978fb9f949141df8b40cd3b8bc9dd54";
        log.info("[DingTalkAspect][afterthrowing] class is {},method is {},error is {}",
                classType,methodName,ex);
        try {
            DingTalkUtils.sendDingTalkMsg(token,classType,methodName,joinPoint.getArgs(),ex);
        } catch (Exception e) {
            log.error("[DingTalkAspect][afterthrowing]sendDingTalkMsg error is {},class is {},method is {}",
                    e,classType,methodName);
        }
    }

    /*@AfterThrowing(value = "@annotation(sendDingTalkMsgOnException)",throwing = "ex")
    public void afterthrowing(JoinPoint joinPoint, Exception ex, SendDingTalkMsgOnException sendDingTalkMsgOnException) {
        Signature joinPointSignature = joinPoint.getSignature();
        Class classType = joinPointSignature.getDeclaringType();
        //String className = joinPointSignature.getDeclaringTypeName();
        String methodName = joinPointSignature.getName();
        String token = sendDingTalkMsgOnException.token();
        log.info("[DingTalkAspect][afterthrowing] class is {},method is {},error is {}",
                classType,methodName,ex);
        try {
            DingTalkUtils.sendDingTalkMsg(token,classType,methodName,joinPoint.getArgs(),ex);
        } catch (Exception e) {
            log.error("[DingTalkAspect][afterthrowing]sendDingTalkMsg error is {},class is {},method is {}",
                    e,classType,methodName);
        }
    }*/

    /*@Around("@annotation(sendDingTalkMsgOnException)")
    public void afterthrowing(ProceedingJoinPoint joinPoint, SendDingTalkMsgOnException sendDingTalkMsgOnException){
        Signature joinPointSignature = joinPoint.getSignature();
        String className = joinPointSignature.getDeclaringTypeName();
        String methodName = joinPointSignature.getName();
        String token = sendDingTalkMsgOnException.token();
        System.out.println(className);
        System.out.println(methodName);
        System.out.println(token);
    }*/
}
