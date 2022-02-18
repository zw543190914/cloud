package com.zw.cloud.tools.question;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        System.out.println(countChar("12b13"));
    }

    static class A {}

    public static ArrayList<Integer> countChar (String str) {
        // write code here
        if (Objects.isNull(str)) {
            return new ArrayList<>();
        }
        int enCount = 0;
        int emptyCount = 0;
        int numberCount = 0;
        int otherCount = 0;
        for(int i = 0; i< str.length(); i ++) {
            char c = str.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                enCount ++;
                continue;
            }
            if (c >= '0' && c <= '9') {
                numberCount ++;
                continue;
            }
            if (c == ' ') {
                emptyCount ++;
                continue;
            }
            otherCount ++;
        }
        ArrayList<Integer> result = new ArrayList<>(4);
        result.add(enCount);
        result.add(emptyCount);
        result.add(numberCount);
        result.add(otherCount);
        return result;
    }
}
