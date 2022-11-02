package com.zw.cloud.mybatis.plus.config.sharding.jdbc;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Slf4j
public class MyPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    /**
     * 分片策略
     */
    @Override
    public String doSharding(Collection<String> tableNameList, PreciseShardingValue<Long> shardingValue) {
        log.info("[MyPreciseShardingAlgorithm][doSharding]tableNameList is {}, SQL执行时传入的分片值: {}", JSON.toJSONString(tableNameList),shardingValue);
        // 根据id 取模拆分到不同的库中
        Long id = shardingValue.getValue();
        for (String tableName : tableNameList) {
            if (tableName.endsWith(String.valueOf(id % tableNameList.size()))) {
                return tableName;
            }
        }
        throw new RuntimeException("分片策略出错");
    }
}
