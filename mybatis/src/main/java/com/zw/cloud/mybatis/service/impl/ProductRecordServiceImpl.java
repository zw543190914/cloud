package com.zw.cloud.mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zw.cloud.mybatis.entity.ProductRecord;
import com.zw.cloud.mybatis.entity.ProductRecordExample;
import com.zw.cloud.mybatis.mapper.ProductRecordMapper;
import com.zw.cloud.mybatis.service.api.IProductRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductRecordServiceImpl implements IProductRecordService {
    @Autowired
    private ProductRecordMapper mapper;

    @Override
    public PageInfo<ProductRecord> queryList(ProductRecord productRecord) {

        PageHelper.startPage(1,10);
        ProductRecordExample example = new ProductRecordExample();
        ProductRecordExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(productRecord.getProductCardCode())) {
            criteria.andProductCardCodeEqualTo(productRecord.getProductCardCode());
        }
        if (Objects.nonNull(productRecord.getStartTime())) {
            criteria.andStartTimeGreaterThanOrEqualTo(productRecord.getStartTime());
        }
        if (Objects.nonNull(productRecord.getCreateTime())) {
            criteria.andCreateTimeGreaterThanOrEqualTo(productRecord.getCreateTime());
        }
        List<ProductRecord> productRecords = mapper.selectByExampleWithBLOBs(example);
        return new PageInfo<ProductRecord>(productRecords);
    }
}
