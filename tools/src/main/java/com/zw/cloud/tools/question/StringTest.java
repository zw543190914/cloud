package com.zw.cloud.tools.question;

public class StringTest {
    public static void main(String[] args) {
        String str = "hello";
        change(str);
        System.out.println(str);
        eq();
    }

    private static void eq(){
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1); // true
        String str2 = new StringBuilder("hello").append("word").toString();
        System.out.println(str2.intern() == str2); // true
        String str3 = new StringBuilder("ja").append("va").toString();
        System.out.println(str3.intern() == str3); // false

    }

    private static void change(String str) {
        str = str + "word";
    }
}
