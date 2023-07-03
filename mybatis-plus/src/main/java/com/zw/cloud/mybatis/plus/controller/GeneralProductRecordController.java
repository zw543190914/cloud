package com.zw.cloud.mybatis.plus.controller;
import java.math.BigDecimal;

import com.zw.cloud.mybatis.plus.entity.GeneralProductRecord;
import com.zw.cloud.mybatis.plus.service.api.IGeneralProductRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 通用生产记录表 前端控制器
 * </p>
 *
 * @author zw
 * @since 2022-12-15
 */
@RestController
@RequestMapping("/general-product-record")
public class GeneralProductRecordController {

    @Autowired
    private IGeneralProductRecordService generalProductRecordService;

    @GetMapping("/save")
    //http://localhost:8080/general-product-record/save
    public GeneralProductRecord save() {
        GeneralProductRecord productRecord = new GeneralProductRecord();
        productRecord.setProductCardCode("14060099");
        productRecord.setDeviceId(1L);
        productRecord.setDeviceName("设备1");
        productRecord.setCraftType("成定");
        productRecord.setOrgCode("devController");
        productRecord.setCraftCode("");
        productRecord.setCraftName("");
        productRecord.setCustomerNo("");
        productRecord.setCustomerName("");
        productRecord.setColorNo("");
        productRecord.setColor("");
        productRecord.setFabricNo("");
        productRecord.setFabricName("");
        productRecord.setFabricWidth("");
        productRecord.setProductWeight("");
        productRecord.setShrinkage(new BigDecimal("0"));
        productRecord.setThickness("");
        productRecord.setCustomerRequest("");
        productRecord.setMatches("");
        productRecord.setSort(0);
        productRecord.setPreStatus(0);
        generalProductRecordService.save(productRecord);
        return productRecord;
    }

    @GetMapping("/getById")
    //http://localhost:8080/general-product-record/getById?id=1628687594906959874
    public GeneralProductRecord getById(@RequestParam Long id) {
        return generalProductRecordService.getById(id);
    }

    @GetMapping("/start")
    //http://localhost:8080/general-product-record/start?id=1628687594906959874
    public boolean start(@RequestParam Long id) {
        return generalProductRecordService.start(id);
    }

    @GetMapping("/finish")
    //http://localhost:8080/general-product-record/finish?id=1628687594906959874
    public boolean finish(@RequestParam Long id) {
        return generalProductRecordService.finish(id);
    }
}
