package com.zw.cloud.tools.test;

import com.zw.cloud.tools.entity.User;
import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;


public class BigDecimalTest {

    public static void main(String[] args) {

        BigDecimal bigDecimal = new BigDecimal("0.9");
        BigDecimal smallDecimal = new BigDecimal("0.8");
        System.out.println(smallDecimal.compareTo(bigDecimal));
        System.out.println(bigDecimal.multiply(new BigDecimal("50")).divide(new BigDecimal(100)));
        Integer temp1 = buildTemp(new BigDecimal("89"), 1);
        System.out.println(temp1);
        System.out.println(buildTemp2(new BigDecimal("89"), 1));

        BigDecimal a1 = new BigDecimal("33");
        BigDecimal a2 = new BigDecimal("44");
        BigDecimal a3 = new BigDecimal("33");
        BigDecimal total = a1.add(a2).add(a3);
        System.out.println(a1.divide(total,3, RoundingMode.HALF_DOWN));
        System.out.println(a2.divide(total,3, RoundingMode.HALF_DOWN));
        System.out.println(a3.divide(total,3, RoundingMode.HALF_DOWN));
        User user = new User();
        user.setId(1L);
        user.setName("");
        user.setDescription("de");
        String format = String.format("%s_%s_%s", user.getId(), user.getName(), user.getDescription());
        System.out.println(format);
        String[] s = format.split("_");
        System.out.println(Arrays.toString(s));

        BigDecimal bigDecimal1 = parseBigDecimal(new BigDecimal("163.545"));

        System.out.println( bigDecimal1.toString());

        BigDecimal bigDecimal2 = parseBigDecimalToInteger(new BigDecimal("163.545"));
        System.out.println( bigDecimal2.toString());

    }

    /**
     * 四舍五入，精确到5
     */
    private static BigDecimal parseBigDecimal(BigDecimal bigDecimal){
        bigDecimal = bigDecimal.setScale(0, RoundingMode.HALF_UP);
        int value = bigDecimal.intValue();
        int gw = value % 10;
        if (gw == 5) {
            return bigDecimal;
        }
        return bigDecimal.divide(new BigDecimal("10"), 0, RoundingMode.HALF_DOWN).multiply(new BigDecimal("10"));
    }

    /**
     * 四舍五入，精确到整数
     */
    private static BigDecimal parseBigDecimalToInteger(BigDecimal bigDecimal){
        if (Objects.isNull(bigDecimal)) {
            return null;
        }
        if (bigDecimal.compareTo(new BigDecimal("100")) > 0) {
            return bigDecimal.divide(new BigDecimal("100"), 0, RoundingMode.HALF_DOWN).multiply(new BigDecimal("100"));

        } else {
            return bigDecimal.setScale(0, RoundingMode.HALF_UP);
        }
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
