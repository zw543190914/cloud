package com.zw.cloud.mybatis.plus.controller;


import com.zw.cloud.mybatis.plus.entity.BaseProductRecord;
import com.zw.cloud.mybatis.plus.service.api.IBaseProductRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private IBaseProductRecordService baseProductRecordService;

    @PostMapping("/insert")
    //http://localhost:8080/base-product-record/insert
    public void insert(@RequestBody BaseProductRecord productRecord) {
        baseProductRecordService.insertBaseProductRecord(productRecord);
    }

    @PostMapping("/queryList")
    //http://localhost:8080/base-product-record/queryList
    public List<BaseProductRecord> queryList(@RequestBody BaseProductRecord productRecord) {
        return baseProductRecordService.queryList(productRecord);
    }
}
