package com.zw.cloud.influxdb.controller;

import com.zw.cloud.influxdb.service.device.report.data.wash.ReverseAlgorithmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;

/**
 * 逆向算法生成
 */
@Slf4j
@RestController
@RequestMapping("/reverse-algorithm")
public class ReverseAlgorithmController {
    @Resource
    private ReverseAlgorithmService reverseAlgorithmService;

    @GetMapping("/reverseAlgorithmWhenFinish/{iotCode}/{minusMinutes}")
    //http://localhost:10010/reverse-algorithm/reverseAlgorithmWhenFinish/1211-zw/3
    public void reverseAlgorithmWhenFinish(@PathVariable String iotCode, @PathVariable Integer minusMinutes) {
        LocalDateTime endTime = LocalDateTime.now();
        reverseAlgorithmService.reverseAlgorithmWhenFinish(Collections.singletonList(iotCode), endTime.minusMinutes(minusMinutes), endTime);
    }
}
