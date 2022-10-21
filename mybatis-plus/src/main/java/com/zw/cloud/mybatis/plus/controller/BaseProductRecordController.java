package com.zw.cloud.mybatis.plus.controller;


import com.zw.cloud.mybatis.plus.entity.ProductRecord;
import com.zw.cloud.mybatis.plus.entity.dto.ProductRecordDTO;
import com.zw.cloud.mybatis.plus.entity.dto.ProductRecordReportQueryDTO;
import com.zw.cloud.mybatis.plus.service.api.IProductRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 生产记录表 前端控制器
 * </p>
 *
 * @author zw
 * @since 2022-09-29
 */
@RestController
@RequestMapping("/base-product-record")
public class BaseProductRecordController {

    @Autowired
    private IProductRecordService baseProductRecordService;

    @PostMapping("/insert")
    //http://localhost:8080/base-product-record/insert
    public void insert(@RequestBody ProductRecord productRecord) {
        baseProductRecordService.insertProductRecord(productRecord);
    }

    @PostMapping("/queryList")
    //http://localhost:8080/base-product-record/queryList
    public List<ProductRecord> queryList(@RequestBody ProductRecord productRecord) {
        return baseProductRecordService.queryList(productRecord);
    }

    @PostMapping("/queryAllFinishedProductForReport")
    //http://localhost:8080/base-product-record/queryAllFinishedProductForReport
    public List<ProductRecordDTO> queryAllFinishedProductForReport(@RequestBody ProductRecordReportQueryDTO productRecordReportQueryDTO) {
        return baseProductRecordService.queryAllFinishedProductForReport(productRecordReportQueryDTO);
    }

    @PostMapping("/pageQueryAllFinishedProduct")
    //http://localhost:8080/base-product-record/pageQueryAllFinishedProduct
    public Map<String, Object> pageQueryAllFinishedProduct(@RequestBody ProductRecordReportQueryDTO productRecordReportQueryDTO) {
        return baseProductRecordService.pageQueryAllFinishedProduct(productRecordReportQueryDTO);
    }
}
