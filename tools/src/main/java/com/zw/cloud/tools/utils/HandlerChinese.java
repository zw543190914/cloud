package com.zw.cloud.tools.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandlerChinese {

    public static void main(String[] args) {
        findChinese();
        filterChinese();
    }
    public static void findChinese(){
        String s = "庄耀(71213),吕建文(66468),王明(57434),";
        String[] split = s.split(",");
        for (String o:split){
            if (StringUtils.isNoneEmpty(o)){
                String regEx="[^0-9]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(o);
                System.out.println( m.replaceAll("").trim());
            }
        }

    }

    //是否为全中文
    public static Boolean filterScope(String text){
        String reg = "[\\u4e00-\\u9fa5]+";
        return text.matches(reg);
    }

    //去除中文
    public static void filterChinese(){
        String applicationOwers ="金强兵(101835),李欣(78397),刘彬彬(zw79103),";
        String[] owers = applicationOwers.trim().split(",");
        for (String s:owers){
            if (StringUtils.isNoneEmpty(s)){
                // 去除中文
                String regexChinese="[\u4e00-\u9fa5]";
                Pattern pat = Pattern.compile(regexChinese);
                Matcher mat = pat.matcher(s);
                String trim = mat.replaceAll("").trim();
                System.out.println(trim);
            }
        }
    }


}
