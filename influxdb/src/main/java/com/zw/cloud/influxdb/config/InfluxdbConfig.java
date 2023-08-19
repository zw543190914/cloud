package com.zw.cloud.influxdb.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
@Slf4j
public class InfluxdbConfig {

/*    @Value("${spring.influx.url}")
    private String influxDBUrl;

    @Value("${spring.influx.user}")
    private String userName;

    @Value("${spring.influx.password}")
    private String password;*/

    @Value("${spring.influx.database}")
    private String database;

    @Value("${spring.influx.retentionPolicy:}")
    private String retentionPolicy;

    @Value("${spring.influx.batchNum:500}")
    private int batchNum;

    @Autowired
    private InfluxDB influxDBClient;

    @PostConstruct
    private void dbSetting() {
        BatchOptions options = BatchOptions.DEFAULTS
                .actions(batchNum)
                .exceptionHandler(((points, throwable) -> log.error("[influxDBClient][dbSetting] influx batch exception, error is ", throwable)));
        this.influxDBClient.enableBatch(options);
        if (StringUtils.isNoneBlank(retentionPolicy)) {
            this.influxDBClient.setRetentionPolicy(retentionPolicy);
        }
        influxDBClient.setDatabase(database);
    }

    @PreDestroy
    public void beforeClose() {
        try {
            influxDBClient.close();
            log.info("[influxDBClient][beforeClose] influx client closed");
        } catch (Exception e) {
            log.warn("[influxDBClient][beforeClose] influx client close error: {}", e.getMessage());
        }
    }

//    @Bean("influxDBClient")
//    public InfluxDB influxDBClient(){
//        InfluxDB influxDB = InfluxDBFactory.connect(influxDBUrl, userName, password);
//        try {
//
//            /**
//             * 异步插入：
//             * enableBatch这里第一个是point的个数，第二个是时间，单位毫秒
//             * point的个数和时间是联合使用的，如果满100条或者60 * 1000毫秒
//             * 满足任何一个条件就会发送一次写的请求。
//             */
//            influxDB.setDatabase(database).enableBatch(500,1000 * 60, TimeUnit.MILLISECONDS);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            //设置默认策略
//            //influxDB.setRetentionPolicy("sensor_retention");
//        }
//        //设置日志输出级别
//        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
//        return influxDB;
//    }

}
