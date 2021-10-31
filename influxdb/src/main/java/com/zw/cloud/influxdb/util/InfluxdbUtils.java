package com.zw.cloud.influxdb.util;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
@Slf4j
public class InfluxdbUtils {

    @Value("${spring.influx.database}")
    private String database;


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

    /**
     * 新增单条记录,利用java的反射机制进行新增操作
     */
    public void insertOne(Object obj){
        //获取度量
        Class<?> clasz = obj.getClass();
        Measurement measurement = clasz.getAnnotation(Measurement.class);
        //构建
        Point.Builder builder = Point.measurement(measurement.name());
        // 获取对象属性
        Field[] fieldArray = clasz.getDeclaredFields();
        Column column = null;
        for(Field field : fieldArray){
            try {
                column = field.getAnnotation(Column.class);
                //设置属性可操作
                field.setAccessible(true);
                if(column.tag()){
                    //tag属性只能存储String类型
                    builder.tag(column.name(), field.get(obj).toString());
                }else{
                    //TODO设置field ==注意类型
                    if(field.get(obj) != null){
                        builder.addField(column.name(), field.get(obj).toString());
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        influxDBClient.write(builder.build());
    }

    /**
     * 批量新增,方法一
     */
    public void insertBatchByRecords(List<?> records){
        List<String> lines = new ArrayList<String>();
        records.forEach(record->{
            Class<?> clasz = record.getClass();
            //获取度量
            Measurement measurement = clasz.getAnnotation(Measurement.class);
            //构建
            Point.Builder builder = Point.measurement(measurement.name());
            Field[] fieldArray = clasz.getDeclaredFields();
            Column column = null;
            for(Field field : fieldArray){
                try {
                    column = field.getAnnotation(Column.class);
                    //设置属性可操作
                    field.setAccessible(true);
                    if(column.tag()){
                        //tag属性只能存储String类型
                        builder.tag(column.name(), field.get(record).toString());
                    }else{
                        //TODO设置field ==注意类型
                        if(field.get(record) != null){
                            builder.addField(column.name(), field.get(record).toString());
                        }
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            lines.add(builder.build().lineProtocol());
        });
        influxDBClient.write(lines);
    }

    /**
     * 批量新增，方法二
     */
    public void insertBatchByPoints(List<?> records){
        BatchPoints batchPoints = BatchPoints.database(database)
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();
        records.forEach(record->{
            Class<?> clasz = record.getClass();
            //获取度量
            Measurement measurement = clasz.getAnnotation(Measurement.class);
            //构建
            Point.Builder builder = Point.measurement(measurement.name());
            Field[] fieldArray = clasz.getDeclaredFields();
            Column column = null;
            for(Field field : fieldArray){
                try {
                    column = field.getAnnotation(Column.class);
                    //设置属性可操作
                    field.setAccessible(true);
                    if(column.tag()){
                        //tag属性只能存储String类型
                        builder.tag(column.name(), field.get(record).toString());
                    }else{
                        //TODO设置field ==注意类型
                        if(field.get(record) != null){
                            builder.addField(column.name(), field.get(record).toString());
                        }
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            batchPoints.point(builder.build());
        });
        influxDBClient.write(batchPoints);
    }

    /**
     * 查询，返回Map集合
     * @param query 完整的查询语句
     * @return
     */
    public List<Object> fetchRecords(String query){
        List<Object> results = new ArrayList<Object>();
        QueryResult queryResult = influxDBClient.query(new Query(query, database));
        queryResult.getResults().forEach(result->{
            result.getSeries().forEach(serial->{
                List<String> columns = serial.getColumns();
                int fieldSize = columns.size();
                serial.getValues().forEach(value->{
                    Map<String,Object> obj = new HashMap<String,Object>();
                    for(int i=0;i<fieldSize;i++){
                        obj.put(columns.get(i), value.get(i));
                    }
                    results.add(obj);
                });
            });
        });
        return results;
    }

    /**
     * 查询，返回map集合
     * @param fieldKeys 查询的字段，不可为空；不可为单独的tag
     * @param measurement 度量，不可为空；
     * @return
     */
    public List<Object> fetchRecords(String fieldKeys, String measurement){
        StringBuilder query = new StringBuilder();
        query.append("select ").append(fieldKeys).append(" from ").append(measurement);
        return this.fetchRecords(query.toString());
    }

    /**
     * 查询，返回map集合
     * @param fieldKeys 查询的字段，不可为空；不可为单独的tag
     * @param measurement 度量，不可为空；
     * @param order
     * @return
     */
    public List<Object> fetchRecords(String fieldKeys, String measurement, String order){
        StringBuilder query = new StringBuilder();
        query.append("select ").append(fieldKeys).append(" from ").append(measurement);
        query.append(" order by ").append(order);
        return this.fetchRecords(query.toString());
    }

    /**
     * 查询，返回map集合
     * @param fieldKeys 查询的字段，不可为空；不可为单独的tag
     * @param measurement 度量，不可为空；
     * @param order
     * @param limit
     * @return
     */
    public List<Object> fetchRecords(String fieldKeys, String measurement, String order, String limit){
        StringBuilder query = new StringBuilder();
        query.append("select ").append(fieldKeys).append(" from ").append(measurement);
        query.append(" order by ").append(order);
        query.append(limit);
        return this.fetchRecords(query.toString());
    }

    /**
     * 查询，返回对象的list集合
     * @param query
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> List<T> fetchResults(String query, Class<?> clasz){
        List results = new ArrayList<>();
        QueryResult queryResult = influxDBClient.query(new Query(query, database));
        queryResult.getResults().forEach(result->{
            result.getSeries().forEach(serial->{
                List<String> columns = serial.getColumns();
                int fieldSize = columns.size();
                serial.getValues().forEach(value->{
                    Object obj = null;
                    try {
                        obj = clasz.getDeclaredConstructor().newInstance();
                        for(int i=0;i<fieldSize;i++){
                            String fieldName = columns.get(i);
                            Field field = clasz.getDeclaredField(fieldName);
                            field.setAccessible(true);
                            Class<?> type = field.getType();
                            if(type == float.class){
                                field.set(obj, Float.valueOf(value.get(i).toString()));
                            }else if (type == Instant.class) {
                                if (Objects.nonNull(value.get(i))) {
                                    field.set(obj,Instant.parse(String.valueOf(value.get(i))));
                                }
                            }else{
                                field.set(obj, value.get(i));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    results.add(obj);
                });
            });
        });
        return results;
    }

    /**
     * 查询，返回对象的list集合
     * @param fieldKeys
     * @param measurement
     * @param clasz
     * @return
     */
    public <T> List<T> fetchResults(String fieldKeys, String measurement, Class<?> clasz){
        StringBuilder query = new StringBuilder();
        query.append("select ").append(fieldKeys).append(" from ").append(measurement);
        return this.fetchResults(query.toString(), clasz);
    }

    /**
     * 查询，返回对象的list集合
     * @param fieldKeys
     * @param measurement
     * @param order
     * @param clasz
     * @return
     */
    public <T> List<T> fetchResults(String fieldKeys, String measurement, String order, Class<?> clasz){
        StringBuilder query = new StringBuilder();
        query.append("select ").append(fieldKeys).append(" from ").append(measurement);
        query.append(" order by ").append(order);
        return this.fetchResults(query.toString(), clasz);
    }

    /**
     * 查询，返回对象的list集合
     * @param fieldKeys
     * @param measurement
     * @param order
     * @param limit
     * @param clasz
     * @return
     */
    public <T> List<T> fetchResults(String fieldKeys, String measurement, String order, String limit, Class<?> clasz){
        StringBuilder query = new StringBuilder();
        query.append("select ").append(fieldKeys).append(" from ").append(measurement);
        query.append(" order by ").append(order);
        query.append(limit);
        return this.fetchResults(query.toString(), clasz);
    }
}
