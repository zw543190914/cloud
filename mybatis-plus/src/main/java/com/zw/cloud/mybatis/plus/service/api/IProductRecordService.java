package com.zw.cloud.mybatis.plus.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.cloud.mybatis.plus.entity.ProductRecord;
import com.zw.cloud.mybatis.plus.entity.dto.ProductRecordDTO;
import com.zw.cloud.mybatis.plus.entity.dto.ProductRecordReportQueryDTO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 生产记录表 服务类
 * </p>
 *
 * @author zw
 * @since 2022-09-29
 */
public interface IProductRecordService extends IService<ProductRecord> {

    List<ProductRecordDTO> queryAllFinishedProductForReport(ProductRecordReportQueryDTO productRecordReportQueryDTO);

    Map<String, Object> pageQueryAllFinishedProduct(ProductRecordReportQueryDTO productRecordReportQueryDTO);

    void insertProductRecord(ProductRecord productRecord);

    void batchInsertProductRecord(List<ProductRecord> productRecordList);

    List<ProductRecord> queryList(ProductRecord productRecord);

}
