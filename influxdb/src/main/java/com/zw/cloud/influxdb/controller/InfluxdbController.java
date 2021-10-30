package com.zw.cloud.influxdb.controller;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import com.zw.cloud.influxdb.entity.Device;
import com.zw.cloud.influxdb.util.InfluxdbUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/influxdb")
public class InfluxdbController {
    @Autowired
    private InfluxdbUtils influxdbUtils;

    /**
     * 插入单挑记录
     */
    @GetMapping("insert1")
    //http://localhost:10010/influxdb/insert1
    public void insert(){
        influxdbUtils.insertOne(buildDevice("1"));
    }

    /**
     * 批量插入第一种方式
     */
    @GetMapping("/batchInsert1")
    //http://localhost:10010/influxdb/batchInsert1
    public void batchInsert1(){
        List<Device> deviceList = new ArrayList<Device>();
        for(int i = 1; i < 51; i++){
            deviceList.add(buildDevice(String.valueOf(i)));
        }
        influxdbUtils.insertBatchByRecords(deviceList);
    }

    /**
     * 批量插入第2种方式
     */
    @GetMapping("/batchInsert2")
    //http://localhost:10010/influxdb/batchInsert2
    public void batchInsert2(){
        List<Device> deviceList = new ArrayList<Device>();
        for(int i = 1; i < 51; i++){
            deviceList.add(buildDevice(String.valueOf(i)));
        }
        influxdbUtils.insertBatchByPoints(deviceList);
    }

    /**
     * 获取数据
     */
    @GetMapping("/datas1")
    //http://localhost:10010/influxdb/datas1?page=1
    public List<Object> datas1(@RequestParam Integer page){
        int pageSize = 10;
        // InfluxDB支持分页查询,因此可以设置分页查询条件
        String pageQuery = " LIMIT " + pageSize + " OFFSET " + (page - 1) * pageSize;

        String queryCondition = "";  //查询条件暂且为空
        // 此处查询所有内容,如果
        String queryCmd = "SELECT * FROM device_report_data"
                // 查询指定设备下的日志信息
                // 要指定从 RetentionPolicyName.measurement中查询指定数据,默认的策略可以不加；
                // + 策略name + "." + measurement
                // 添加查询条件(注意查询条件选择tag值,选择field数值会严重拖慢查询速度)
                + queryCondition
                // 查询结果需要按照时间排序
                + " ORDER BY time DESC"
                // 添加分页查询条件
                + pageQuery;

        return influxdbUtils.fetchRecords(queryCmd);
    }

    /**
     * 获取数据
     */
    @GetMapping("/datas2")
    //http://localhost:10010/influxdb/datas2?page=1
    public List<Device> datas2(@RequestParam Integer page){
        int pageSize = 10;
        // InfluxDB支持分页查询,因此可以设置分页查询条件
        String pageQuery = " LIMIT " + pageSize + " OFFSET " + (page - 1) * pageSize;

        String queryCondition = "";  //查询条件暂且为空
        // 此处查询所有内容,如果
        String queryCmd = "SELECT * FROM device_report_data"
                // 查询指定设备下的日志信息
                // 要指定从 RetentionPolicyName.measurement中查询指定数据,默认的策略可以不加；
                // + 策略name + "." + measurement
                // 添加查询条件(注意查询条件选择tag值,选择field数值会严重拖慢查询速度)
                + queryCondition
                // 查询结果需要按照时间排序
                + " ORDER BY time DESC"
                // 添加分页查询条件
                + pageQuery;
        return influxdbUtils.fetchResults(queryCmd, Device.class);
        //List<Sensor> sensorList = influxdbUtils.fetchResults("*", "sensor", Sensor.class);
    }


    private Device buildDevice(String value){
        Device device = new Device();
        Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        device.setTime(Instant.ofEpochSecond(second));
        device.setRtime(0L);
        device.setCtime(0L);
        device.setMessageType(0);
        device.setEventCode(0);
        device.setSpeedSetting(new BigDecimal(value));
        device.setSpeed(new BigDecimal(value));
        device.setInClothTension(new BigDecimal(value));
        device.setOutClothTension(new BigDecimal(value));
        device.setRollingCarLiquidLevel(new BigDecimal(value));
        device.setDryingRoomPresetTemp1(new BigDecimal(value));
        device.setDryingRoomActualTemp1(new BigDecimal(value));
        device.setDryingRoomPresetTemp2(new BigDecimal(value));
        device.setDryingRoomActualTemp2(new BigDecimal(value));
        device.setDryingRoomPresetTemp3(new BigDecimal(value));
        device.setDryingRoomActualTemp3(new BigDecimal(value));
        device.setDryingRoomPresetTemp4(new BigDecimal(value));
        device.setDryingRoomActualTemp4(new BigDecimal(value));
        device.setDryingRoomPresetTemp5(new BigDecimal(value));
        device.setDryingRoomActualTemp5(new BigDecimal(value));
        device.setDryingRoomPresetTemp6(new BigDecimal(value));
        device.setDryingRoomActualTemp6(new BigDecimal(value));
        device.setDryingRoomPresetTemp7(new BigDecimal(value));
        device.setDryingRoomActualTemp7(new BigDecimal(value));
        device.setDryingRoomPresetTemp8(new BigDecimal(value));
        device.setDryingRoomActualTemp8(new BigDecimal(value));
        device.setDryingRoomPresetTemp9(new BigDecimal(value));
        device.setDryingRoomActualTemp9(new BigDecimal(value));
        device.setTotalAmplitude(new BigDecimal(value));
        device.setDryingRoomDoorWidth(new BigDecimal(value));
        device.setTopFeed(new BigDecimal(value));
        device.setLowerFeed(new BigDecimal(value));
        device.setLeftBrushFeed(new BigDecimal(value));
        device.setRightBrushFeed(new BigDecimal(value));
        device.setInClothFeed(new BigDecimal(value));
        device.setOutClothFeed(new BigDecimal(value));
        device.setFallingFeed(new BigDecimal(value));
        device.setSwingFeed(new BigDecimal(value));
        device.setSpeciWindSpeed1(new BigDecimal(value));
        device.setWindSpeed1(new BigDecimal(value));
        device.setSpeciWindSpeed2(new BigDecimal(value));
        device.setWindSpeed2(new BigDecimal(value));
        device.setSpeciWindSpeed3(new BigDecimal(value));
        device.setWindSpeed3(new BigDecimal(value));
        device.setSpeciWindSpeed4(new BigDecimal(value));
        device.setWindSpeed4(new BigDecimal(value));
        device.setSpeciCycleWindSpeed1(new BigDecimal(value));
        device.setCycleWindSpeed1(new BigDecimal(value));
        device.setSpeciCycleWindSpeed2(new BigDecimal(value));
        device.setCycleWindSpeed2(new BigDecimal(value));
        device.setSpeciCycleWindSpeed3(new BigDecimal(value));
        device.setCycleWindSpeed3(new BigDecimal(value));
        device.setSpeciCycleWindSpeed4(new BigDecimal(value));
        device.setCycleWindSpeed4(new BigDecimal(value));
        device.setSpeciCycleWindSpeed5(new BigDecimal(value));
        device.setCycleWindSpeed5(new BigDecimal(value));
        device.setSpeciCycleWindSpeed6(new BigDecimal(value));
        device.setCycleWindSpeed6(new BigDecimal(value));
        device.setSpeciCycleWindSpeed7(new BigDecimal(value));
        device.setCycleWindSpeed7(new BigDecimal(value));
        device.setSpeciCycleWindSpeed8(new BigDecimal(value));
        device.setCycleWindSpeed8(new BigDecimal(value));
        device.setSpeciCycleWindSpeed9(new BigDecimal(value));
        device.setCycleWindSpeed9(new BigDecimal(value));
        device.setTroughTemp(new BigDecimal(value));
        device.setStopSignal(0);
        device.setAllowStartSignal(0);
        device.setPartDeviceStartStatus(0);
        device.setWarnSignal(0);
        device.setSpeciWindSpeed5(new BigDecimal(value));
        device.setWindSpeed5(new BigDecimal(value));
        device.setSpeciWindSpeed6(new BigDecimal(value));
        device.setWindSpeed6(new BigDecimal(value));
        device.setDryingRoomPresetTemp10(new BigDecimal(value));
        device.setDryingRoomActualTemp10(new BigDecimal(value));
        device.setSpeciCycleWindSpeed10(new BigDecimal(value));
        device.setCycleWindSpeed10(new BigDecimal(value));
        return device ;
    }

}
