package com.zw.cloud.tools.test;

public class RecuesionTest {

    public static void main(String[] args) {
        test(4); // 2  2  3
    }

    private static void test(int num) {
        if (num > 2 ){
            num --;
            test(num);
        }
        System.out.println("num = " + num);

    }
}
