package com.zw.cloud.tools.utils;

import com.zw.cloud.tools.entity.User;
import org.stringtemplate.v4.ST;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTemplete {

    public static void main(String[] args) {
        test();
    }

    private String test02(Map<String,Object> dataMap) throws Exception{
        String templateRootPath = this.getClass().getClassLoader().getResource("templates").getFile();
        File templateRootDir = new File(templateRootPath);
        File templateFiile = null;
        for (File template : templateRootDir.listFiles()) {
            if (template.getName().contains("index2")){
                templateFiile = template;
            }
        }
        if (null != templateFiile && templateFiile.exists()){
            return viewResolver(dataMap,templateFiile);
        }
        return null;
    }

    private String viewResolver(Map<String,Object> dataMap,File template)throws Exception{
        StringBuilder sb = new StringBuilder();
        //“r” 以只读方式来打开指定文件夹
        RandomAccessFile ra = new RandomAccessFile(template, "r");
        String line = null;
        while (null !=(line = ra.readLine())){
            line = new String(line.getBytes("ISO-8859-1"),"utf-8");
            Matcher matcher = matcher(line);
            while (matcher.find()){
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    // 把 $ 中间字符串取出来
                    String param = matcher.group(i);
                    Object paramValue = dataMap.get(param);
                    if (null == paramValue){continue;}
                    line = line.replaceAll("\\$\\{" + param + "}", paramValue.toString());
                }
            }
            sb.append(line);

        }

        return sb.toString();
    }

    private static void test01(){
        String template = "<user.name> <user.age>";
        ST st = new ST(template);
        User user = new User();
        user.setName("zw");
        user.setAge((byte)22);
        st.add("user", user);
        System.out.println(st.render());

    }

    private static void test(){
        String template = "单号{billNo},描述{desc" +
                "ription},详情{detail}";
        Map<String,String> dataMap = new HashMap<>();
        dataMap.put("billNo","001");
        dataMap.put("description","描述信息...");
        dataMap.put("detail","详情...");
        StringBuilder result = new StringBuilder();
        Matcher matcher = matcher2(template);
        while (matcher.find()){
            for (int i = 1; i <= matcher.groupCount(); i++) {
                // 把 {} 中间字符串取出来
                String param = matcher.group(i);
                Object paramValue = dataMap.get(param);
                if (null == paramValue){continue;}
                template = template.replaceAll("\\{" + param + "}", paramValue.toString());
            }
        }
        System.out.println(template);
    }

    private static Matcher matcher(String str){
        Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(str);
    }

    private static Matcher matcher2(String str){
        Pattern pattern = Pattern.compile("\\{(.*?)\\}", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(str);
    }

}
