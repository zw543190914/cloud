package com.zw.cloud.common.utils.bean;

import cn.hutool.core.bean.copier.CopyOptions;

public class BeanUtils extends cn.hutool.core.bean.BeanUtil {

    /**
     * bean copy 忽略 source 中的空值
     */
    public static void copyIgnoreNullValue(Object source, Object target) {
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.ignoreNullValue();
        cn.hutool.core.bean.BeanUtil.copyProperties(source,target, copyOptions);
    }
}
