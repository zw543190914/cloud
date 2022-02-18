package com.zw.cloud.tools.question;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SortedString {
    /**
     * 字符串排序（只改变字母顺序、其他位置不变）
     * 规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。
     * 规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。
     * 规则 3 ：非英文字母的其它字符保持原来的位置。
     *
     * 示例1
     * 输入：
     * A Famous Saying: Much Ado About Nothing (2012/8).
     *
     * 输出：
     * A aaAAbc dFgghh: iimM nNn oooos Sttuuuy (2012/8).
     */
    public static void main(String[] args) {

        System.out.println(sort("A Famous Saying: Much Ado About Nothing (2012/8)."));
    }

    public static String sort(String str){
        String source = "abcdefghijklmnopqrstuvwxyz";

        // 字母集合
        List<String> charCodes = new ArrayList<>();
        char[] result = new char[str.length()];
        //首先拿出所有的非字母，放入新数组
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isLetter(c)) {
                result[i] = c;
            }
        }
        System.out.println(String.valueOf(result));
        //将字母提取按照顺序排序（出现两次的字母按照输入顺序进行排序，忽略ASCII的大小）
        for (int i = 0; i < source.length(); i++) {
            char original = source.charAt(i);
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                String curr = String.valueOf(c);
                if (StringUtils.equalsIgnoreCase(String.valueOf(original),curr)) {
                    charCodes.add(curr);
                }
            }
        }
        System.out.println(charCodes.toString());
        //将字母输入
        for (int i = 0; i < charCodes.size(); i++) {
            for (int j = 0; j < result.length; j++) {
                //字符数组某一位是否为空时
                if (result[j] == '\0') {
                    result[j] = charCodes.get(i).charAt(0);
                    break;
                }
            }
        }
        return String.valueOf(result);
    }

}
