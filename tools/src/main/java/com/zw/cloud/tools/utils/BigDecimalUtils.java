package com.zw.cloud.tools.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zw.cloud.tools.entity.User;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;


public class BigDecimalUtils {

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

        System.out.println( bigDecimal1.toPlainString());

        System.out.println("=========精确到100=========");
        System.out.println( parseBigDecimalToInteger(new BigDecimal("1368.545")));
        System.out.println(new BigDecimal("150.545").divide(new BigDecimal("100"), 0, RoundingMode.HALF_UP).multiply(new BigDecimal("100")));
        System.out.println(new BigDecimal("150").divide(new BigDecimal("100"), 0, RoundingMode.HALF_UP).multiply(new BigDecimal("100")));
        System.out.println("=========去除小数点后的0=========");
        System.out.println(new BigDecimal("1368.00").stripTrailingZeros().toPlainString());

        System.out.println("=========精确到5=========");
        System.out.println(parseBigDecimalToFive(new BigDecimal("282.43")));
        System.out.println(parseBigDecimalToFive(new BigDecimal("235.55")));
        System.out.println(parseBigDecimalToFive(new BigDecimal("1037.5")));
        System.out.println(parseBigDecimalToFive(new BigDecimal("339.23")));

        System.out.println(parseBigDecimalToFive(new BigDecimal("140")));
        System.out.println(parseBigDecimalToFive(new BigDecimal("342.5")));
        System.out.println(parseBigDecimalToFive(new BigDecimal("443.5")));
        System.out.println("=========精确到5 end=========");

        ArrayList<BigDecimal> bigDecimalArrayList = Lists.newArrayList(new BigDecimal(17), new BigDecimal(18),
                new BigDecimal(17), new BigDecimal(17),
                new BigDecimal(19), new BigDecimal(19));
        System.out.println(filterMaxSameValueFromList(bigDecimalArrayList));
        System.out.println(getAvgByList(bigDecimalArrayList));
        ArrayList<Integer> list = Lists.newArrayList(null, null);
        System.out.println(list.stream().filter(Objects::nonNull).max(Comparator.comparing(v -> v)).isPresent());
        Object obj = null;
        System.out.println(MapUtils.isNotEmpty((Map)obj));
        String k = "dryingRoomPresetTemp12";
        String key = k.substring(k.lastIndexOf("p") + 1);
        System.out.println(key);
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
     * 如 37.5 ≤ A < 42.5，则A取值为 40；如 32.5 ≤ A < 37.5，则A取值为 35。
     * 如 35 ≤ A < 40，则A取值为 40；如 30 ≤ A < 35，则A取值为 35。
     */
    private static BigDecimal parseBigDecimalToFive(BigDecimal bigDecimal){
        bigDecimal = bigDecimal.subtract(new BigDecimal("2.5")).setScale(2, RoundingMode.HALF_UP);
        int value = bigDecimal.intValue();
        // 个位
        int gw = value % 10;
        BigDecimal result = bigDecimal.divide(new BigDecimal("10"), 0, RoundingMode.HALF_UP).multiply(new BigDecimal("10"));
        if (gw >= 5) {
            return result;
        }
        return result.add(new BigDecimal("5"));
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

    /**
     * 取集合中相同值最多的，存在多个取最大值
     */
    private static BigDecimal filterMaxSameValueFromList(List<BigDecimal> valueList) {
        if (CollectionUtils.isEmpty(valueList)) {
            return null;
        }
        // k->数值，v->出现次数
        Map<Integer,Integer> map = new HashMap<>();
        for (BigDecimal value : valueList) {
            if (Objects.isNull(value)) {
                continue;
            }
            int intValue = value.intValue();
            Integer count = map.get(intValue);
            if (Objects.isNull(count)) {
                map.put(intValue,1);
            } else {
                map.put(intValue,count + 1);
            }
        }
        Integer maxValue = filterMaxSameValueFromMap(map);
        if (Objects.isNull(maxValue)) {
            return null;
        }
        return new BigDecimal(maxValue);
    }

    /**
     * 取相同最多的值(如相同值有多项，取最大值)
     */
    private static Integer filterMaxSameValueFromMap(Map<Integer, Integer> tempMap){
        // map  k->温度，v-> 出现次数
        Integer temp = null;
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : tempMap.entrySet()) {
            Integer happenCount = entry.getValue();
            Integer actualValue = entry.getKey();
            if (Objects.isNull(temp)) {
                temp = actualValue;
                count = happenCount;
                continue;
            }
            if (happenCount == count) {
                temp = temp > actualValue ? temp : actualValue;
                continue;
            }
            if (happenCount > count) {
                temp = actualValue;
                count = happenCount;
            }
        }
        return temp;
    }

    /**
     * 取集合中相同值最多的，存在多个取最大值
     * key -> 第几节
     * value -> key:数字，value:出现次数
     */
    public static Map<String,Integer> filterEachSectionMaxSameValueFromMap(Map<String, Map<Integer, Integer>> dryingRoomActualTempMap){
        // 取相同最多的值  按节分组
        Map<String,Integer> dryingRoomActualMap = new LinkedHashMap<>();
        dryingRoomActualTempMap.forEach((k,map) -> {
            Integer temp = filterMaxSameValueFromMap(map);
            if (Objects.nonNull(temp)) {
                dryingRoomActualMap.put(k,temp);
            }
        });
        return dryingRoomActualMap;
    }

    /**
     * 求平均数
     */
    public static BigDecimal getAvgByList(List<BigDecimal> args) {
        if (CollectionUtils.isEmpty(args)) {
            return null;
        }
        // 过滤空值
        args = args.stream().filter(Objects::nonNull).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(args)) {
            return null;
        }
        BigDecimal total = BigDecimal.ZERO;
        for (BigDecimal arg : args) {
            total = total.add(arg);
        }
        return total.divide(new BigDecimal(args.size()), 2, RoundingMode.HALF_UP);
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
