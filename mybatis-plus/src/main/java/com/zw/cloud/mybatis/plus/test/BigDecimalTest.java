package com.zw.cloud.mybatis.plus.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalTest {

    public static void main(String[] args) {
        System.out.println(getFluctuationUp(new BigDecimal("10"),new BigDecimal("2")));
    }

    public static BigDecimal getFluctuationUp(BigDecimal up, BigDecimal val) {
        if (up == null || val == null || up.compareTo(BigDecimal.ZERO) <= 0 || val.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        //BigDecimal b = val.multiply(up.add(new BigDecimal(100)).divide(new BigDecimal(100)));
        BigDecimal b = val.multiply(up.divide(new BigDecimal("100"),1, RoundingMode.HALF_UP).add(BigDecimal.ONE));

        //保留两位小数;
        return b.setScale(1, RoundingMode.HALF_UP);
    }

}
