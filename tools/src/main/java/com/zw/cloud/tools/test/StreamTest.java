package com.zw.cloud.tools.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
        statistics();
        flatMap();
    }

    private static void statistics() {
        List<Integer> number = Lists.newArrayList(1, 2, 5, 4);
        IntSummaryStatistics statistics = number.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : "+statistics.getMax());
        System.out.println("列表中最小的数 : "+statistics.getMin());
        System.out.println("平均数 : "+statistics.getAverage());
        System.out.println("所有数之和 : "+statistics.getSum());
    }

    private static void flatMap() {
        Map<String, BigDecimal> map1 = new HashMap<>();
        map1.put("1",new BigDecimal(1));
        map1.put("2",new BigDecimal(2));
        map1.put("3",new BigDecimal(3));
        Map<String,BigDecimal> map2 = new HashMap<>();
        map2.put("2",new BigDecimal(2));
        map2.put("4",new BigDecimal(4));
        Map<String, BigDecimal> collect = Stream.of(map1, map2).flatMap(v -> v.entrySet().stream()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, BigDecimal::add));
        System.out.println(JSON.toJSONString(collect));
    }
}
