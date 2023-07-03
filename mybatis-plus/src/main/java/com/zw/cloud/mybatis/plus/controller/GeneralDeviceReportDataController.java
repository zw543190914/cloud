package com.zw.cloud.mybatis.plus.controller;


import com.alibaba.fastjson2.JSON;
import com.zw.cloud.mybatis.plus.entity.GeneralDeviceReportData;
import com.zw.cloud.mybatis.plus.service.api.IGeneralDeviceReportDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 通用设备上报数据 前端控制器
 * </p>
 *
 * @author zw
 * @since 2023-05-11
 */
@RestController
@RequestMapping("/general-device-report-data")
@Slf4j
public class GeneralDeviceReportDataController {

    @Resource
    private IGeneralDeviceReportDataService deviceReportDataService;

    @PostMapping
    //http://127.0.0.1:8082/general-device-report-data
    public boolean saveOrUpdate(@RequestBody GeneralDeviceReportData generalDeviceReportData) {
        log.info("[GeneralDeviceReportDataController][saveOrUpdate]generalDeviceReportData is {}", JSON.toJSONString(generalDeviceReportData));
        return deviceReportDataService.saveOrUpdate(generalDeviceReportData);
    }

}
