package com.zw.cloud.storage.dao;

import com.zw.cloud.storage.entity.StorageTbl;
import com.zw.cloud.storage.entity.StorageTblExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StorageTblMapper {
    long countByExample(StorageTblExample example);

    int deleteByExample(StorageTblExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StorageTbl record);

    int insertSelective(StorageTbl record);

    List<StorageTbl> selectByExample(StorageTblExample example);

    StorageTbl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StorageTbl record, @Param("example") StorageTblExample example);

    int updateByExample(@Param("record") StorageTbl record, @Param("example") StorageTblExample example);

    int updateByPrimaryKeySelective(StorageTbl record);

    int updateByPrimaryKey(StorageTbl record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table storage_tbl
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<StorageTbl> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table storage_tbl
     *
     * @mbg.generated
     * @author hewei
     */
    StorageTbl selectOneByExample(StorageTblExample example);
}