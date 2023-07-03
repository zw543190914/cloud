package com.zw.cloud.common.utils;

import com.google.common.base.CaseFormat;

public class StringTransforUtils {
    public static void main(String[] args) {
        System.out.println("驼峰转下划线："+ lowerCamelToUnderscore("userName"));
        System.out.println("下划线转驼峰："+ lowerUnderscoreToCamel("user_name"));
    }

    /**
     * 驼峰转下划线
     * userName -> user_name
     */
    public static String lowerCamelToUnderscore(String source) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, source);
    }

    /**
     * 下划线转驼峰
     * user_name -> userName
     */
    public static String lowerUnderscoreToCamel(String source) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, source);
    }
}
