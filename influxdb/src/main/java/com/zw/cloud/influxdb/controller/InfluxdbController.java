package com.zw.cloud.influxdb.controller;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.zw.cloud.influxdb.entity.Device;
import com.zw.cloud.influxdb.entity.DeviceVO;
import com.zw.cloud.influxdb.service.InfluxdbQueryService;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/influxdb")
public class InfluxdbController {
    @Autowired
    private InfluxdbQueryService influxdbQueryService;

    @Value("${spring.influx.database}")
    private String database;

    // 数据库的表
    private String measurement = "device_report_data";

    @Autowired
    private InfluxDB influxDB;


    /**
     * 插入
     */
    @GetMapping("/insert")
    //http://localhost:10010/influxdb/insert
    public void insert(){
        Device device = buildDevice(38f, 66,"dfgs");
        Point point = Point.measurementByPOJO(device.getClass()).addFieldsFromPOJO(device).build();
        influxDB.write(point);
    }

    /**
     * 批量插入
     */
    @GetMapping("/batchInsert")
    //http://localhost:10010/influxdb/batchInsert
    public void batchInsert(){
        Device device = buildDevice(33f, 66,"dfgs");
        BatchPoints.Builder builder = BatchPoints.builder();
        Lists.newArrayList(device).forEach(m -> builder.point(Point.measurementByPOJO(m.getClass()).addFieldsFromPOJO(m).build()));
        //写入
        influxDB.write(builder.build());
    }

    /**
     * 删除数据
     */
    @GetMapping("/delete")
    //http://localhost:10010/influxdb/delete
    public QueryResult delete(){
        String command = "delete from device_report_data where device='rr' and dataType = 'report' and cycleWindSpeed1 = 75;";
        /**
         * fields not supported in WHERE clause during deletion
         */
        return influxDB.query(new Query(command, database));
    }

    /**
     * 批量插入第一种方式
     */
    @GetMapping("/batchInsert1")
    //http://localhost:10010/influxdb/batchInsert1?stop=11
    public void insert(Float stop){
        List<String> lines = new ArrayList<>();
        Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        Point point = buildingPoint(Float.valueOf(second),stop);
        lines.add(point.lineProtocol());
        //写入
        influxDB.write(lines);
    }

    /**
     * 批量插入第2种方式
     */
    @GetMapping("/batchInsert2")
    //http://localhost:10010/influxdb/batchInsert2
    public void batchInsert1(Float stop){
        BatchPoints batchPoints = BatchPoints
                .database(database)
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();
        //遍历sqlserver获取数据
        for(int i = 0;i < 50 ; i++){
            //创建单条数据对象——表名
            Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
            Point point = buildingPoint(Float.valueOf(second),stop);
            //将单条数据存储到集合中
            batchPoints.point(point);
        }
        //批量插入
        influxDB.write(batchPoints);
    }

    /**
     * 获取数据
     */
    @GetMapping("/query")
    //http://localhost:10010/influxdb/query?page=1&device=dfgs
    public List<DeviceVO> query(@RequestParam Integer page,@RequestParam String device){
        int pageSize = 10;
        // InfluxDB支持分页查询,因此可以设置分页查询条件

        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minusMinutes(5);
        Map<String, Object> bindParams = new HashMap<>();
        String sql = "SELECT * FROM device_report_data  where time >= $startTime and time <= $endTime and device=$device and dataType = 'report' order by time asc LIMIT $pageSize OFFSET $startOffSet";
        bindParams.put("startTime", startTime);
        bindParams.put("endTime", endTime);
        bindParams.put("device",device);
        bindParams.put("pageSize", 10);
        bindParams.put("startOffSet", (page - 1) * pageSize);
        log.info("[InfluxdbController][query]sql is {}",sql);
        return influxdbQueryService.query(sql,DeviceVO.class,bindParams);
    }

    private Point buildingPoint(Float second,Float stop){
        return Point.measurement(measurement)
                .addField("rtime", second)
                .addField("ctime", second)
                .addField("messageType", 1f)
                .addField("eventCode", 8830f)
                .addField("speed", stop)
                .addField("speedSetting", stop)
                .addField("dryingRoomPresetTemp1", 121f)
                .addField("dryingRoomPresetTemp2", 121f)
                .addField("dryingRoomPresetTemp3", 121f)
                .addField("dryingRoomPresetTemp5", 121f)
                .addField("dryingRoomPresetTemp6", 121f)
                .addField("dryingRoomPresetTemp7", 121f)
                .addField("dryingRoomPresetTemp8", 121f)
                .addField("dryingRoomPresetTemp9", 121f)
                .addField("dryingRoomPresetTemp10", 121f)
                .addField("speciWindSpeed1", 78f)
                .addField("speciWindSpeed2", 78f)
                .addField("speciWindSpeed3", 78f)
                .addField("speciWindSpeed4", 78f)
                .addField("speciWindSpeed5", 78f)
                .addField("speciWindSpeed6", 78f)
                .addField("speciWindSpeed7", 78f)
                .addField("speciWindSpeed8", 78f)
                .addField("speciWindSpeed9", 78f)
                .addField("windSpeed1", 78f)
                .addField("windSpeed2", 99f)
                .addField("windSpeed3", 99f)
                .addField("windSpeed4", 99f)
                .addField("windSpeed5", 99f)
                .addField("windSpeed6", 99f)
                .addField("windSpeed7", 99f)
                .addField("windSpeed8", 99f)
                .addField("windSpeed9", 99f)
                .addField("dataType","report")
                .build();
    }
    private Device buildDevice(Float value, Integer stop,String iotCode){
        Device device = new Device();
        Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        Instant instant = Instant.ofEpochSecond(second);
        device.setTime(instant);
        device.setRtime(Float.valueOf(String.valueOf(second)));
        device.setCtime(Float.valueOf(String.valueOf(second)));
        device.setMessageType(1f);
        device.setEventCode(8830f);
        device.setSpeedSetting(value);
        device.setSpeed(value);
        device.setInClothTension(value);
        device.setOutClothTension(value);
        device.setRollingCarLiquidLevel(value);
        device.setDryingRoomPresetTemp1(value);
        device.setDryingRoomActualTemp1(value);
        device.setDryingRoomPresetTemp2(value);
        device.setDryingRoomActualTemp2(value);
        device.setDryingRoomPresetTemp3(value);
        device.setDryingRoomActualTemp3(value);
        device.setDryingRoomPresetTemp4(value);
        device.setDryingRoomActualTemp4(value);
        device.setDryingRoomPresetTemp5(value);
        device.setDryingRoomActualTemp5(value);
        device.setDryingRoomPresetTemp6(value);
        device.setDryingRoomActualTemp6(value);
        device.setDryingRoomPresetTemp7(value);
        device.setDryingRoomActualTemp7(value);
        device.setDryingRoomPresetTemp8(value);
        device.setDryingRoomActualTemp8(value);
        device.setDryingRoomPresetTemp9(value);
        device.setDryingRoomActualTemp9(value);
        device.setTotalAmplitude(value);
        device.setDryingRoomDoorWidth(value);
        device.setTopFeed(value);
        device.setLowerFeed(value);
        device.setLeftBrushFeed(value);
        device.setRightBrushFeed(value);
        device.setInClothFeed(value);
        device.setOutClothFeed(value);
        device.setFallingFeed(value);
        device.setSwingFeed(value);
        device.setSpeciWindSpeed1(value);
        device.setWindSpeed1(value);
        device.setSpeciWindSpeed2(value);
        device.setWindSpeed2(value);
        device.setSpeciWindSpeed3(value);
        device.setWindSpeed3(value);
        device.setSpeciWindSpeed4(value);
        device.setWindSpeed4(value);
        device.setSpeciCycleWindSpeed1(value);
        device.setCycleWindSpeed1(value);
        device.setSpeciCycleWindSpeed2(value);
        device.setCycleWindSpeed2(value);
        device.setSpeciCycleWindSpeed3(value);
        device.setCycleWindSpeed3(value);
        device.setSpeciCycleWindSpeed4(value);
        device.setCycleWindSpeed4(value);
        device.setSpeciCycleWindSpeed5(value);
        device.setCycleWindSpeed5(value);
        device.setSpeciCycleWindSpeed6(value);
        device.setCycleWindSpeed6(value);
        device.setSpeciCycleWindSpeed7(value);
        device.setCycleWindSpeed7(value);
        device.setSpeciCycleWindSpeed8(value);
        device.setCycleWindSpeed8(value);
        device.setSpeciCycleWindSpeed9(value);
        device.setCycleWindSpeed9(value);
        device.setTroughTemp(value);
        device.setStopSignal((float)stop);

        device.setSpeciWindSpeed5(value);
        device.setWindSpeed5(value);
        device.setSpeciWindSpeed6(value);
        device.setWindSpeed6(value);
        device.setDryingRoomPresetTemp10(value);
        device.setDryingRoomActualTemp10(value);
        device.setSpeciCycleWindSpeed10(value);
        device.setCycleWindSpeed10(value);
        device.setDataType("report");
        device.setDevice(iotCode);
        return device ;
    }

}
