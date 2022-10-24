package com.zw.cloud.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zw.cloud.mybatis.plus.entity.ProductRecord;
import com.zw.cloud.mybatis.plus.entity.dto.ProductRecordReportQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 生产记录表 Mapper 接口
 * </p>
 *
 * @author zw
 * @since 2022-09-29
 */
public interface ProductRecordMapper extends BaseMapper<ProductRecord> {

    List<ProductRecord> queryAllFinishedProductForReportById(@Param("record") ProductRecordReportQueryDTO productRecordReportQueryDTO);

    List<Long> queryAllFinishedProductIdListForReport(@Param("record") ProductRecordReportQueryDTO productRecordReportQueryDTO);

    List<ProductRecord> queryAllFinishedProductForReport(@Param("record") ProductRecordReportQueryDTO productRecordReportQueryDTO);

    List<ProductRecord> pageQueryAllFinishedProductForReport(@Param("record") ProductRecordReportQueryDTO productRecordReportQueryDTO);

    int countAllFinishedProductForReport(@Param("record") ProductRecordReportQueryDTO productRecordReportQueryDTO);

    List<ProductRecord> queryProductRecordExceptionList(@Param("record") ProductRecordReportQueryDTO productRecordReportQueryDTO);

}
