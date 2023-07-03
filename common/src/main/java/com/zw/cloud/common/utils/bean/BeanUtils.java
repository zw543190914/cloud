package com.zw.cloud.common.utils.bean;

import cn.hutool.core.bean.copier.CopyOptions;
import com.zw.cloud.common.utils.bean.annotation.CopyField;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Objects;

@Slf4j
public class BeanUtils extends cn.hutool.core.bean.BeanUtil {

    /**
     * bean copy 忽略 source 中的空值
     */
    public static void copyIgnoreNullValue(Object source, Object target) {
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.ignoreNullValue();
        cn.hutool.core.bean.BeanUtil.copyProperties(source,target, copyOptions);
    }

    public static void copyByCopyField(Object source, Object target) {
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();
        Field[] sourceFields = sourceClass.getDeclaredFields();
        for (Field sourceField : sourceFields) {
            CopyField annotation = sourceField.getAnnotation(CopyField.class);
            if (Objects.isNull(annotation)) {
                continue;
            }
            sourceField.setAccessible(true);
            String targetFieldName = annotation.targetFieldName();
            try {
                Field targetField = targetClass.getDeclaredField(targetFieldName);
                targetField.setAccessible(true);
                targetField.set(target,sourceField.get(source));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.warn("[BeanUtils][copyByCopyField]sourceField is {},targetFieldName is {},copy error is ",sourceField.getName(),targetFieldName,e);
            }
        }

    }

}
