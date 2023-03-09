package com.zw.cloud.mybatis.mapper;

import com.zw.cloud.mybatis.entity.ProductRecord;
import com.zw.cloud.mybatis.entity.ProductRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductRecordMapper {
    long countByExample(ProductRecordExample example);

    int deleteByExample(ProductRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductRecord record);

    int insertSelective(ProductRecord record);

    List<ProductRecord> selectByExampleWithBLOBs(ProductRecordExample example);

    List<ProductRecord> selectByExample(ProductRecordExample example);

    ProductRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductRecord record, @Param("example") ProductRecordExample example);

    int updateByExampleWithBLOBs(@Param("record") ProductRecord record, @Param("example") ProductRecordExample example);

    int updateByExample(@Param("record") ProductRecord record, @Param("example") ProductRecordExample example);

    int updateByPrimaryKeySelective(ProductRecord record);

    int updateByPrimaryKeyWithBLOBs(ProductRecord record);

    int updateByPrimaryKey(ProductRecord record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table product_record
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<ProductRecord> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table product_record
     *
     * @mbg.generated
     * @author hewei
     */
    ProductRecord selectOneByExample(ProductRecordExample example);
}