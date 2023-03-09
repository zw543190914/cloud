package com.zw.cloud.mybatis.controller;

import com.github.pagehelper.PageInfo;
import com.zw.cloud.common.utils.WebResult;
import com.zw.cloud.mybatis.entity.ProductRecord;
import com.zw.cloud.mybatis.service.api.IProductRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product-record")
@Slf4j
public class ProductRecordController {

    @Autowired
    private IProductRecordService productRecordService;

    @PostMapping("/queryList")
    //http://localhost:8080/product-record/queryList
    public WebResult<PageInfo<ProductRecord>> queryList(@RequestBody ProductRecord productRecord) {
        return WebResult.build(productRecordService.queryList(productRecord));
    }
}
