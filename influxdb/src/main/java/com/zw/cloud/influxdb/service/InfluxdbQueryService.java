package com.zw.cloud.influxdb.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import com.zw.cloud.common.utils.bean.BeanUtils;
import com.zw.cloud.influxdb.config.InfluxDbExtendMapper;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
@Slf4j
public class InfluxdbQueryService {

    @Autowired
    private InfluxDB influxDBClient;

    @PreDestroy
    public void beforeClose() {
        try {
            influxDBClient.close();
            log.info("influx client closed");
        } catch (Exception e) {
            log.warn("influx client close error: {}", e.getMessage());
        }
    }

    public <M> List<M> query(String query, Class<M> clz, Map<String, Object> bindParams) {
        Query q = getQueryWithBindParams(query, bindParams);
        QueryResult queryResult = influxDBClient.query(q);
        InfluxDbExtendMapper resultMapper = new InfluxDbExtendMapper();
        return resultMapper.toPOJO(queryResult, clz);
    }

    private Query getQueryWithBindParams(String query, Map<String, Object> bindParams) {
        BoundParameterQuery.QueryBuilder builder = BoundParameterQuery.QueryBuilder.newQuery(query);
        if (bindParams != null) {
            bindParams.keySet().forEach(k -> {
                Object value = bindParams.get(k);
                if (value instanceof LocalDateTime) {
                    ZoneId zoneId = ZoneId.systemDefault();
                    LocalDateTime ldt = (LocalDateTime) value;
                    value = ldt.atZone(zoneId).toInstant();
                }
                builder.bind(k, value);
            });
        }
        return builder.create();
    }
}
