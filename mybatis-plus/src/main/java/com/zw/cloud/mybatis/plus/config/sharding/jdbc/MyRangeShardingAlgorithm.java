//package com.zw.cloud.mybatis.plus.config.sharding.jdbc;
//
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
//import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
//
//import java.math.BigInteger;
//import java.util.Collection;
//import java.util.LinkedHashSet;
//
//@Slf4j
//public class MyRangeShardingAlgorithm implements RangeShardingAlgorithm<Long> {
//
//    @Override
//    public Collection<String> doSharding(Collection<String> tableNameList, RangeShardingValue<Long> shardingValue) {
//        log.info("[MyRangeShardingAlgorithm][doSharding]tableNameList is {}, shardingValue: {}", JSON.toJSONString(tableNameList), shardingValue);
//        Collection<String> result = new LinkedHashSet<>(tableNameList.size());
//        long minValue = shardingValue.getValueRange().hasLowerBound() ? shardingValue.getValueRange().lowerEndpoint() : Integer.MIN_VALUE;
//        long maxValue = shardingValue.getValueRange().hasUpperBound() ? shardingValue.getValueRange().upperEndpoint() : Long.MAX_VALUE;
//        // 最大值减最小值，得到差
//        long range = BigInteger.valueOf(maxValue).subtract(BigInteger.valueOf(minValue)).longValue();
//        // 最小值得绝对值除10的余数
//        long begin = Math.abs(minValue) % 2;
//        // 超过2直接返回可用的表名，这里的2是，自己的分片策略值
//        // 假设我的分片策略是：对id除以2，取余数
//        if (range > 2) {
//            return tableNameList;
//        }
//        // 如果差在分片策略内的，就直接取余数，得到对应的表名
//        for (long i = begin; i <= range; i += 1) {
//            for (String each : tableNameList) {
//                if (each.endsWith(String.valueOf(i))) {
//                    result.add(each);
//                }
//            }
//        }
//        return result;
//
//    }
//}
