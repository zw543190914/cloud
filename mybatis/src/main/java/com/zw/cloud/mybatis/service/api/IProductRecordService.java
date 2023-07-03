package com.zw.cloud.mybatis.service.api;

import com.github.pagehelper.PageInfo;
import com.zw.cloud.mybatis.entity.ProductRecord;

import java.util.List;

public interface IProductRecordService {

    PageInfo<ProductRecord> queryList(ProductRecord productRecord);

    ProductRecord insert(ProductRecord productRecord);
}
