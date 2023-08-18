package com.zw.cloud.influxdb.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import com.zw.cloud.influxdb.config.InfluxDbExtendMapper;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import javax.annotation.PreDestroy;


@Component
@Slf4j
public class InfluxdbQueryService {

    @Autowired
    @Qualifier("deviceReportDataInfluxdb")
    private InfluxDB deviceReportDataInfluxdb;


    @PreDestroy
    public void beforeClose() {
        try {
            deviceReportDataInfluxdb.close();
            log.info("deviceReportDataInfluxdb client closed");
        } catch (Exception e) {
            log.warn("influx client close error: {}", e.getMessage());
        }
    }

    public <M> List<M> queryDeviceReportData(String query, Class<M> clz, Map<String, Object> bindParams) {
        Query q = getQueryWithBindParams(query, bindParams);
        QueryResult queryResult = deviceReportDataInfluxdb.query(q);
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
