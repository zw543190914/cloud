package com.zw.cloud.netty.ide.aop;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson2.JSON;
import com.zw.cloud.netty.ide.annotation.Ide;
import com.zw.cloud.netty.ide.enums.IdeTypeEnum;
import com.zw.cloud.netty.ide.exception.IdeException;
import com.zw.cloud.netty.utils.RedisLockUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class IdeAspect {

    private final ThreadLocal<String> PER_FIX_KEY = new ThreadLocal<String>();

    /**
     * 配置注解后 默认开启
     */
    private final boolean enable = true;

    /**
     * request请求头中的key
     */
    private final static String HEADER_RID_KEY = "RID";

    /**
     * redis中锁的key前缀
     */
    private static final String IDE_REDIS_KEY_PREFIX = "IDE:";
    /**
     * redis中锁的key前缀
     */
    private static final String RID_REDIS_KEY_PREFIX = IDE_REDIS_KEY_PREFIX + "RID:";
    /**
     * redis中锁的key前缀
     */
    private static final String PARAMS_REDIS_KEY_PREFIX = IDE_REDIS_KEY_PREFIX + "PARAMS:";


    private final RedisLockUtil redisLockUtil;

    @Pointcut("@annotation(com.zw.cloud.netty.ide.annotation.Ide)")
    public void watchIde() {

    }

    @Before("watchIde()")
    public void doBefore(JoinPoint joinPoint) throws Exception{
        Ide ide = getAnnotation(joinPoint);
        if (enable && null != ide) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            String className = method.getDeclaringClass().getName();
            String methodName = method.getName();
            String hostIp = InetAddress.getLocalHost().getHostAddress();
            String perFix = ide.perFix();
            String redisKey = String.format("%s:%s:%s:%s:", perFix, hostIp, className, methodName);
            //1.判断模式
            if (ide.ideTypeEnum() == IdeTypeEnum.RID) {
                String rid = request.getHeader(HEADER_RID_KEY);
                if (StrUtil.isNotBlank(rid)) {
                    Boolean result = redisLockUtil.tryLock(RID_REDIS_KEY_PREFIX + redisKey + rid, "",ide.timeOut());
                    if (!result) {
                        log.error("msg=不允许重复执行,,key={}", PARAMS_REDIS_KEY_PREFIX + redisKey + rid);
                        throw new IdeException("命中RID重复请求");
                    }
                    PER_FIX_KEY.set(RID_REDIS_KEY_PREFIX + redisKey + rid);
                    log.debug("msg=当前请求已成功记录,且标记为0未处理,,{}={}", HEADER_RID_KEY, rid);
                } else {
                    log.warn("msg=header没有rid,防重复提交功能失效,,remoteHost={}" + request.getRemoteHost());
                }
            }
            //2.参数值模式
            if (ide.ideTypeEnum() == IdeTypeEnum.PARAMS) {
                Object[] paramValues = joinPoint.getArgs();
                String params = JSON.toJSONString(paramValues);
                String paramsKey = SecureUtil.md5(params);
                Boolean result = redisLockUtil.tryLock(PARAMS_REDIS_KEY_PREFIX + redisKey + paramsKey,"", ide.timeOut());
                if (!result) {
                    log.error("msg=不允许重复执行,,key={}", PARAMS_REDIS_KEY_PREFIX + redisKey + paramsKey);
                    throw new IdeException("不允许重复提交");
                }
                PER_FIX_KEY.set(PARAMS_REDIS_KEY_PREFIX + redisKey + paramsKey);
                log.debug("msg=当前请求已成功记录,且标记为0未处理,,PARAMS={}", params);
            }

        }
    }


    /*@After("watchIde()")
    public void doAfter(JoinPoint joinPoint) {
        try {
            Ide ide = getAnnotation(joinPoint);
            if (enable && null != ide) {
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
                if (ide.ideTypeEnum() == IdeTypeEnum.RID) {
                    String rid = request.getHeader(HEADER_RID_KEY);
                    if (StrUtil.isNotBlank(rid)) {
                        try {
                            //redisLockUtil.releaseLock(PER_FIX_KEY.get(),"");
                            log.info("msg=当前请求已成功处理,,rid={}", rid);
                        } catch (Exception e) {
                            log.error("释放redis锁异常", e);
                        }
                    }
                    PER_FIX_KEY.remove();
                }

                if (ide.ideTypeEnum() == IdeTypeEnum.PARAMS) {
                    try {
                        //redisLockUtil.releaseLock(PER_FIX_KEY.get(),"");
                        log.info("msg=当前请求已成功处理,,PARAMS={}", PER_FIX_KEY.get());
                    } catch (Exception e) {
                        log.error("释放redis锁异常", e);
                    }
                    PER_FIX_KEY.remove();
                }

            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }*/

    private Ide getAnnotation(JoinPoint joinPoint) throws Exception{
         //获取方法名
        String methodName = joinPoint.getSignature().getName();
         //反射获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
         //拿到方法对应的参数类型
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
         //根据类、方法、参数类型（重载）获取到方法的具体信息
        Method objMethod = targetClass.getMethod(methodName, parameterTypes);
         //拿到方法定义的注解信息
        return objMethod.getDeclaredAnnotation(Ide.class);
    }
}

