package com.zw.cloud.tools.test;

import java.math.BigDecimal;


public class BigDecimalTest {

    public static void main(String[] args) {

        BigDecimal bigDecimal = new BigDecimal("0.9");
        BigDecimal smallDecimal = new BigDecimal("0.8");
        System.out.println(smallDecimal.compareTo(bigDecimal));
        System.out.println(bigDecimal.multiply(new BigDecimal("50")).divide(new BigDecimal(100)));
    }
}
