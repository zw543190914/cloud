package com.zw.cloud.influxdb.service.device.report.data.wash;

import com.zw.cloud.influxdb.entity.IotInfoDo;
import com.zw.cloud.influxdb.service.InfluxdbQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class DeviceReportDataWashQueryService {

    @Autowired
    private InfluxdbQueryService influxdbQueryService;

    /**
     * 查询influxDb实时数据
     */
    public List<IotInfoDo> queryDeviceReportDataWashByTime(List<String> iotCodeList, LocalDateTime startTime, LocalDateTime endTime) {
        Assert.notNull(startTime,"startTime is null");
        Assert.notNull(endTime,"endTime is null");

        //1.查询采集器信息
        if (CollectionUtils.isEmpty(iotCodeList)) {
            return Collections.emptyList();
        }
        //2.查询influxDb原数据
        Map<String, Object> bindParams = new HashMap<>();
        StringBuilder str = new StringBuilder("SELECT * FROM device_report_data_wash where 1=1 and deviceType = 'wash' and ");
        for (int i = 0; i < iotCodeList.size(); i++) {
            str.append("device = $device" + i + " or ");
            bindParams.put("device" + i, iotCodeList.get(i));
        }
        String sql = str.substring(0, str.length() - 4) + " and time >= $startTime and time <= $endTime order by time asc";

        bindParams.put("startTime", startTime);
        bindParams.put("endTime", endTime);

        log.info("[DeviceReportDataWashQueryService][queryDeviceReportDataWashByTime]sql is {}",sql);
        return influxdbQueryService.queryDeviceReportData(sql, IotInfoDo.class, bindParams);
    }

}
