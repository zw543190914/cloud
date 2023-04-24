package com.zw.cloud.common.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 定型采集点位告警
 * 实时数据采集 需要监控告警点
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MonitoringPoint {

    String chineseDesc() default "";

}
