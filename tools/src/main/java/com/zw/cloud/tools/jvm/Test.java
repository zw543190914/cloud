package com.zw.cloud.tools.jvm;

public class Test {
    private int age = 999;

    public static void main(String[] args) {
        Class<Test> clazz = Test.class;
        Test test = new Test();
        System.out.println("hello");
    }
}
