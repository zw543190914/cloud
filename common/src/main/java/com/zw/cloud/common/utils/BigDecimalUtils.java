package com.zw.cloud.common.utils;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
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
        System.out.println("取集合中相同值最多的，存在多个取最大值:" + filterMaxSameValueFromList(bigDecimalArrayList));
        System.out.println(getAvgByList(bigDecimalArrayList));

        System.out.println("====取集合中相同值最多的，存在多个取最后一个====");
        System.out.println(filterMaxSameAndLastValueFromList(Lists.newArrayList(BigDecimal.valueOf(1),BigDecimal.valueOf(22),
                BigDecimal.valueOf(33),BigDecimal.valueOf(33),BigDecimal.valueOf(44),BigDecimal.valueOf(44)
                ,BigDecimal.valueOf(1),BigDecimal.valueOf(22)),2));

        System.out.println("====连续出现2次(含)以上数值中的最大值，如有效时间段内无连续值，则取最大值；精确到整数====");
        System.out.println("连续出现数字:" + consecutiveDigits(Lists.newArrayList(BigDecimal.valueOf(1),BigDecimal.valueOf(2),
                BigDecimal.valueOf(1),BigDecimal.valueOf(3),BigDecimal.valueOf(3),BigDecimal.valueOf(3),BigDecimal.valueOf(4),
                BigDecimal.valueOf(4),BigDecimal.valueOf(4),BigDecimal.valueOf(5))));
        System.out.println("连续出现数字:" + consecutiveDigits(Lists.newArrayList(BigDecimal.valueOf(1),BigDecimal.valueOf(2),
                BigDecimal.valueOf(7),BigDecimal.valueOf(5))));

        ArrayList<Integer> list = Lists.newArrayList(null, null);
        System.out.println(list.stream().filter(Objects::nonNull).max(Comparator.comparing(v -> v)).isPresent());
        Object obj = null;
        System.out.println(MapUtils.isNotEmpty((Map)obj));
        String k = "dryingRoomPresetTemp12";
        String key = k.substring(k.lastIndexOf("p") + 1);
        System.out.println(key);
        Map<String,Object> map = new HashMap<>();
        map.put("test1","");
        map.put("test5","11");
        map.put("test2","3");
        map.put("test7","13");
        map.put("test4",null);
        System.out.println(findLastValueFromJsonObject(map, "test"));
        map.put("test7",new BigDecimal("2"));
        System.out.println(new BigDecimal(map.get("test7").toString()));
        System.out.println(doubleRound(5.3));
        System.out.println(doubleRound(5.5));
        System.out.println(doubleRound(5.8));

        System.out.println(getAvgWithFilterFirstSecondAndLast(Lists.newArrayList("1", "2", "3","4")).toPlainString());

        BigDecimal bigDecimal2 = new BigDecimal("5.23").setScale(0, RoundingMode.DOWN);
        System.out.println(bigDecimal2);
        BigDecimal bigDecimal3 = new BigDecimal("5.98").setScale(0, RoundingMode.DOWN);
        System.out.println(bigDecimal3);
        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("test1","1");
        jsonMap.put("test5","11");
        jsonMap.put("test2","3");
        jsonMap.put("test7","13");
        jsonMap.put("test4",null);
        System.out.println("JSON 中最大值:" + findMaxValueFromJsonObject(jsonMap));
        Map<String, Object> testMap = fillEveryOneJsonValue(jsonMap, 20, "test", 10);
        System.out.println("为JSON 中每一节赋值:" + JSON.toJSONString(testMap));
        System.out.println(JSON.toJSONString(buildJsonForRecommendCraft(testMap,BigDecimal.valueOf(40),"test",12)));

        LinkedHashMap<String, BigDecimal> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("k1",BigDecimal.valueOf(11));
        linkedHashMap.put("k2",BigDecimal.valueOf(12));
        linkedHashMap.put("k3",BigDecimal.valueOf(13));
        linkedHashMap.put("k4",BigDecimal.valueOf(14));
        linkedHashMap.put("k5",BigDecimal.valueOf(15));
        System.out.println("去掉前2个点位和最后2个点位，取剩余点位最大值:" + queryMaxAfterFilterFirstSecondAndLastValue(linkedHashMap));
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
     * 取集合中相同值最多的，存在多个取最后一个,排除空值
     */
    public static BigDecimal filterMaxSameAndLastValueFromList(List<BigDecimal> valueList,int scale) {
        if (CollectionUtils.isEmpty(valueList)) {
            return null;
        }
        // k->数值，v->出现次数
        Map<BigDecimal,Integer> map = new LinkedHashMap<>();
        for (BigDecimal value : valueList) {
            if (Objects.isNull(value)) {
                continue;
            }
            Integer count = map.remove(value);
            if (Objects.isNull(count)) {
                map.put(value,1);
            } else {
                map.put(value,count + 1);
            }
        }
        return filterMaxSameAndLastValueFromList(map,scale);
    }

    /**
     * 取集合中相同值最多的，存在多个取最后一个
     */
    private static BigDecimal filterMaxSameAndLastValueFromList(Map<BigDecimal, Integer> tempMap,int scale){
        if (MapUtils.isEmpty(tempMap)) {
            return null;
        }
        // map  k->温度，v-> 出现次数
        BigDecimal result = null;
        int count = 0;
        for (Map.Entry<BigDecimal, Integer> entry : tempMap.entrySet()) {
            Integer happenCount = entry.getValue();
            BigDecimal actualValue = entry.getKey();
            if (Objects.isNull(result) || happenCount >= count) {
                result = actualValue;
                count = happenCount;
            }
        }
        return Objects.isNull(result) ? null : result.setScale(scale,RoundingMode.HALF_UP);
    }



    /**
     * 连续出现2次(含)以上数值中的最大值，如有效时间段内无连续值，则取最大值；精确到整数
     */
    public static BigDecimal consecutiveDigits(List<BigDecimal> valueList) {
        if (CollectionUtils.isEmpty(valueList)) {
            return null;
        }
        Set<BigDecimal> consecutiveDigits = new HashSet<>();
        for (int i = 0; i < valueList.size() - 1; i++) {
            if (valueList.get(i).compareTo(valueList.get(i + 1)) == 0) {
                consecutiveDigits.add(valueList.get(i));
            }
        }
        if (CollectionUtils.isNotEmpty(consecutiveDigits)) {
            Optional<BigDecimal> max = consecutiveDigits.stream().filter(Objects::nonNull).max(Comparator.comparing(s -> s));
            return max.map(bigDecimal -> bigDecimal.setScale(0, RoundingMode.HALF_UP)).orElse(null);
        }
        Optional<BigDecimal> max = valueList.stream().filter(Objects::nonNull).max(Comparator.comparing(s -> s));
        return max.map(bigDecimal -> bigDecimal.setScale(0, RoundingMode.HALF_UP)).orElse(null);
    }

    /**
     * map截取
     */
    public static LinkedHashMap<String, BigDecimal> subMap(LinkedHashMap<String, BigDecimal> iotMap,String prefix,Integer count) {
        LinkedHashMap<String, BigDecimal> resultMap = new LinkedHashMap<>();
        if (MapUtils.isEmpty(iotMap)) {
            return resultMap;
        }
        if (Objects.isNull(count)) {
            return resultMap;
        }
        for (int i = 1; i <= count; i++) {
            resultMap.put(prefix + i,iotMap.get(prefix + i));
        }
        return resultMap;
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
     * 查询 JSON 中有值的最后一个
     */
    public static Object findLastValueFromJsonObject(Object jsonValue,String prefix){
        if (Objects.isNull(jsonValue)) {
            return null;
        }
        Map<String, Object> jsonMap = JSON.parseObject(JSON.toJSONString(jsonValue), Map.class);
        if (MapUtils.isEmpty(jsonMap)) {
            return null;
        }
        Object result = null;
        int index = 0;
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (Objects.isNull(value) || StringUtils.isBlank(String.valueOf(value))) {
                continue;
            }
            int currentIndex = Integer.parseInt(key.replace(prefix, ""));
            if (currentIndex > index) {
                index = currentIndex;
                result = value;
            }
        }
        return result;
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

    /**
     * 计算json对象中平均值
     *
     * @param data     json
     * @param lastChar 最后一个非数字字符，主要用于排序，
     *                 因为计算要 去掉前两节和最后一节，其他节数排除值为空的
     *                 eg:json对象的key为 dryingRoomPresetTemp1 传 p
     * @return
     */
    public static BigDecimal caleJsonObjectAvg(Object data, String lastChar) {
        // 值有空串，也有整数和小数。。。用 object
        Map<String, Object> dataMap = JSON.parseObject(JSON.toJSONString(data), Map.class);
        if (MapUtils.isEmpty(dataMap)) {
            return null;
        }
        TreeMap<Integer, String> sortedMap = new TreeMap<>();

        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            try {
                String k = entry.getKey();
                if (Objects.isNull(entry.getValue())) {
                    continue;
                }
                String v = String.valueOf(entry.getValue());
                if (StringUtils.isBlank(v)) {
                    continue;
                }
                // 0值不参与计算
                if (StringUtils.equals("0",v)) {
                    continue;
                }
                String key = k.substring(k.lastIndexOf(lastChar) + 1);
                sortedMap.put(Integer.parseInt(key), v);
            } catch (Exception e) {
                log.warn("[CaleJsonDataAvgUtils][caleAvg] data is {},warn is {}", JSON.toJSONString(data), e);
            }
        }
        List<String> dryingRoomList = Lists.newArrayList(sortedMap.values());
        return getAvgWithFilterFirstSecondAndLast(dryingRoomList);
    }

    /**
     * 去掉前两节和最后一节，其他节数排除值为空的
     * 求平均数
     */
    private static BigDecimal getAvgWithFilterFirstSecondAndLast(List<String> args) {
        if (CollectionUtils.isEmpty(args) || args.size() < 4) {
            return null;
        }
        // 去掉前两节和最后一节，其他节数排除值为空的
        args = args.subList(2, args.size() - 1);

        BigDecimal num = new BigDecimal(0);
        BigDecimal oneBigDecimal = new BigDecimal(1);
        BigDecimal total = BigDecimal.ZERO;
        for (String arg : args) {
            if (arg != null) {
                total = total.add(new BigDecimal(arg));
                num = num.add(oneBigDecimal);
            }
        }
        return total.divide(num, 0, RoundingMode.HALF_UP);
    }


    /**
     * 去掉前2个点位和最后2个点位，取剩余点位最大值
     * 不排除空值，固定去除对应节数
     */
    public static BigDecimal queryMaxAfterFilterFirstSecondAndLastValue(LinkedHashMap<String, BigDecimal> dryingRoomTempMap){
        if (MapUtils.isEmpty(dryingRoomTempMap)) {
            return null;
        }
        List<BigDecimal> valueList = new ArrayList<>(dryingRoomTempMap.size());
        dryingRoomTempMap.forEach((k,v) -> valueList.add(v));
        if (valueList.size() < 5) {
            return null;
        }
        // 去掉前边2节和最后2节烘箱温度数据
        Optional<BigDecimal> max = valueList.subList(2, valueList.size() - 2).stream().filter(Objects::nonNull).max(Comparator.comparing(v -> v));
        return max.orElse(null);
    }

    /**
     * JSON 中最大值
     */
    public static BigDecimal findMaxValueFromJsonObject(Object jsonValue){
        if (Objects.isNull(jsonValue)) {
            return null;
        }
        Map<String, Object> dryingRoomPresetTempMap = JSON.parseObject(JSON.toJSONString(jsonValue), Map.class);
        if (MapUtils.isEmpty(dryingRoomPresetTempMap)) {
            return null;
        }
        Collection<Object> values = dryingRoomPresetTempMap.values();
        Optional<BigDecimal> max = values.stream().filter(Objects::nonNull).map(s -> new BigDecimal(String.valueOf(s))).max(Comparator.comparing(s -> s));
        if (max.isEmpty()) {
            return null;
        }
        return max.get();
    }

    /**
     * 为JSON 中每一节赋值
     */
    public static Map<String,Object> fillEveryOneJsonValue(Object jsonValue,Object totalValue,String prefix,Integer tempCount) {
        if (Objects.isNull(totalValue)) {
            return null;
        }
        // 每一节赋值
        Map<String,Object> jsonMap = new LinkedHashMap<>();
        Map<String,Object> oldMap = new LinkedHashMap<>();
        if (Objects.nonNull(jsonValue)) {
            oldMap = JSON.parseObject(JSON.toJSONString(jsonValue), Map.class);
        }
        for (int i = 1; i <= tempCount ; i++) {
            Object value = Optional.ofNullable(oldMap.get(prefix + i)).orElse(totalValue);
            jsonMap.put(prefix + i, value);
        }
        return jsonMap;
    }


    //    如果大于10节 ，最后一节补充为标准工艺最后一节，其他节优先取标准工艺对应节数，没有对应的取总值，总值为空则数据为空
    //    机台 1  2  ...  9  10 11  12
    //    标准 1  2  ... 9   总  总  10
    //    小于 10 节 ，最后一节补充为标准工艺最后一节，其他节优先取标准工艺对应节数
    //    机台 1  2  ...  7  8
    //    标准 1  2  ... 7  10
    public static LinkedHashMap<String, BigDecimal> buildJsonForRecommendCraft(Object jsonData,BigDecimal totalVal, String jsonKey, Integer roomCount) {
        LinkedHashMap<String, BigDecimal> resultMap = Maps.newLinkedHashMap();
        if (Objects.isNull(roomCount) || roomCount <= 0 || StringUtils.isBlank(jsonKey)) {
            return resultMap;
        }

        if (Objects.isNull(totalVal) && Objects.isNull(jsonData)) {
            //都没值
            return resultMap;
        }
        if (Objects.isNull(jsonData)) {
            // 取总值填充
            for (int i = 1; i <= roomCount; i++) {
                resultMap.put(jsonKey + i, totalVal);
            }
            return resultMap;
        }

        LinkedHashMap<String, Object> oldMap = JSON.parseObject(JSON.toJSONString(jsonData), LinkedHashMap.class);
        if (Objects.isNull(totalVal)) {
            // 总值没有值 ,取json最大值作为总值
            totalVal = findMaxValueFromJsonObject(oldMap);
        }
        if (Objects.isNull(totalVal)) {
            return resultMap;
        }
        Object tenValue = oldMap.remove(jsonKey + 10);
        BigDecimal lastValue = Objects.isNull(tenValue) ? totalVal : new BigDecimal(String.valueOf(tenValue));
        for (int i = 1; i < roomCount; i++) {
            Object currValue = oldMap.get(jsonKey + i);
            if (Objects.isNull(currValue)) {
                resultMap.put(jsonKey + i, totalVal);
            } else {
                resultMap.put(jsonKey + i, new BigDecimal(String.valueOf(currValue)));
            }
        }

        resultMap.put(jsonKey + roomCount, lastValue);
        return resultMap;
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

    /**
     * double 四舍五入取整
     */
    private static long doubleRound(double number){
        // Double number1 四舍五入
        return Math.round(number);
    }

    /**
     * 获取map中第一个有值的数据
     */
    public static BigDecimal filterFirstValueFromMap(LinkedHashMap<String, BigDecimal> valueMap) {
        if (MapUtils.isEmpty(valueMap)) {
            return null;
        }
        for (Map.Entry<String, BigDecimal> entry : valueMap.entrySet()) {
            if (Objects.nonNull(entry.getValue())) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * 获取map中最后一个有值的数据
     */
    public static BigDecimal filterLastValueFromMap(LinkedHashMap<String, BigDecimal> valueMap) {
        if (MapUtils.isEmpty(valueMap)) {
            return null;
        }
        BigDecimal lastValue = null;
        for (Map.Entry<String, BigDecimal> entry : valueMap.entrySet()) {
            if (Objects.nonNull(entry.getValue())) {
                lastValue = entry.getValue();
            }
        }
        return lastValue;
    }

}
