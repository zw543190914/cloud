package com.zw.cloud.tools.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class SalaryTest {
    public static void main(String[] args) {
        System.out.println(cale(new BigDecimal(""),new BigDecimal((3290 + 3500) * 12 + 4000)));
        /*BigDecimal salary = new BigDecimal("");
        int decreaseAvg = (1500 + 1000) * 12;
        BigDecimal decrease = new BigDecimal(decreaseAvg);
        BigDecimal total = salary.multiply(new BigDecimal(12));
        System.out.println(cale(total,decrease));*/
    }

    /**
     *
     */
    private static BigDecimal cale(BigDecimal total,BigDecimal decrease) {
        if (Objects.isNull(total)) {
            return null;
        }
        if (Objects.nonNull(decrease)) {
            total = total.subtract(decrease);
        }
        if (total.compareTo(new BigDecimal(60000)) <= 0) {
            return null;
        }
        total = total.subtract(new BigDecimal(60000));
        if (total.compareTo(new BigDecimal(0)) <= 0) {
            return null;
        }
        // 不超过36000元的 3% 速算扣除数 0
        if (total.compareTo(new BigDecimal(36000)) <= 0) {
            return total.multiply(new BigDecimal("0.03")).setScale(2, RoundingMode.HALF_UP);
        }
        // 超过36000元至144000元的部分 10% 速算扣除数 2520
        if (total.compareTo(new BigDecimal(144000)) <= 0) {
            return total.multiply(new BigDecimal("0.1")).subtract(new BigDecimal(2520)).setScale(2, RoundingMode.HALF_UP);
        }
        // 超过144000元至300000元的部分 20% 速算扣除数 16920
        if (total.compareTo(new BigDecimal(300000)) <= 0) {
            return total.multiply(new BigDecimal("0.2")).subtract(new BigDecimal(16920)).setScale(2, RoundingMode.HALF_UP);
        }
        // 超过300000元至420000元的部分 25% 速算扣除数 31920
        if (total.compareTo(new BigDecimal(420000)) <= 0) {
            return total.multiply(new BigDecimal("0.25")).subtract(new BigDecimal(31920)).setScale(2, RoundingMode.HALF_UP);
        }
        // 超过420000元至660000元的部分 30% 速算扣除数 52920
        if (total.compareTo(new BigDecimal(660000)) <= 0) {
            return total.multiply(new BigDecimal("0.3")).subtract(new BigDecimal(52920)).setScale(2, RoundingMode.HALF_UP);
        }
        // 超过660000元至960000元的部分 35% 速算扣除数 85920
        if (total.compareTo(new BigDecimal(660000)) <= 0) {
            return total.multiply(new BigDecimal("0.35")).subtract(new BigDecimal(85920)).setScale(2, RoundingMode.HALF_UP);
        }
        // 超过960000元的部分 45% 速算扣除数 181920
        return total.multiply(new BigDecimal("0.45")).subtract(new BigDecimal(181920)).setScale(2, RoundingMode.HALF_UP);

    }
}
