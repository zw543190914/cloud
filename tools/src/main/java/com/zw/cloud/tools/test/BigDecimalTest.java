package com.zw.cloud.tools.test;

import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;


public class BigDecimalTest {

    public static void main(String[] args) {

        BigDecimal bigDecimal = new BigDecimal("0.9");
        BigDecimal smallDecimal = new BigDecimal("0.8");
        System.out.println(smallDecimal.compareTo(bigDecimal));
        System.out.println(bigDecimal.multiply(new BigDecimal("50")).divide(new BigDecimal(100)));
        Integer temp1 = buildTemp(new BigDecimal("189"), 1);
        System.out.println(temp1);
        System.out.println(buildTemp2(new BigDecimal("189"), 1));
    }

    private static  Integer buildTemp(BigDecimal num, int size) {
        if (ObjectUtils.isEmpty(num)) {
            return null;
        }
        int temp = num.divide(new BigDecimal(size), 2, BigDecimal.ROUND_HALF_UP).intValue();
        if (temp < 0) {
            return 0;
        }
        int res = temp / 10;
        //个位直接%10
        int ge = temp % 10;
        if (ge <= 2) {
            ge = 0;
            if (temp < 10) {
                return ge;
            }
        } else if (ge <= 7) {
            ge = 5;
            if (temp < 10) {
                return ge;
            }
        } else {
            if (temp < 10) {
                return 10;
            }
            int shiwei = (temp / 10 + 1) * 10;//十位直接%100
            res = temp / 100 * 100 + shiwei;
            return res;
        }
        return res * 10 + ge;
    }

    private static  Integer buildTemp2(BigDecimal num, int size) {
        if (ObjectUtils.isEmpty(num)) {
            return null;
        }
        int temp = num.divide(new BigDecimal(size), 2, BigDecimal.ROUND_HALF_UP).intValue();
        if (temp < 0) {
            return 0;
        }
        int res = temp / 10;
        //个位直接%10
        int ge = temp % 10;
        if (ge <= 2) {
            ge = 0;
        } else if (ge <= 7) {
            ge = 5;
        } else {
            ge = 10;
        }
        return res * 10 + ge;
    }

}
