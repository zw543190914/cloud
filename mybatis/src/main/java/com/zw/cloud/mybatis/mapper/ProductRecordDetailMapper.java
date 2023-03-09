package com.zw.cloud.mybatis.mapper;

import com.zw.cloud.mybatis.entity.ProductRecordDetail;
import com.zw.cloud.mybatis.entity.ProductRecordDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductRecordDetailMapper {
    long countByExample(ProductRecordDetailExample example);

    int deleteByExample(ProductRecordDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductRecordDetail record);

    int insertSelective(ProductRecordDetail record);

    List<ProductRecordDetail> selectByExample(ProductRecordDetailExample example);

    ProductRecordDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductRecordDetail record, @Param("example") ProductRecordDetailExample example);

    int updateByExample(@Param("record") ProductRecordDetail record, @Param("example") ProductRecordDetailExample example);

    int updateByPrimaryKeySelective(ProductRecordDetail record);

    int updateByPrimaryKey(ProductRecordDetail record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table product_record_detail
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<ProductRecordDetail> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table product_record_detail
     *
     * @mbg.generated
     * @author hewei
     */
    ProductRecordDetail selectOneByExample(ProductRecordDetailExample example);
}