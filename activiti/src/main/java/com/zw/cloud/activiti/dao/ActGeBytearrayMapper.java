package com.zw.cloud.activiti.dao;

import com.zw.cloud.activiti.entity.ActGeBytearray;
import com.zw.cloud.activiti.entity.ActGeBytearrayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActGeBytearrayMapper {
    long countByExample(ActGeBytearrayExample example);

    int deleteByExample(ActGeBytearrayExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActGeBytearray record);

    int insertSelective(ActGeBytearray record);

    List<ActGeBytearray> selectByExampleWithBLOBs(ActGeBytearrayExample example);

    List<ActGeBytearray> selectByExample(ActGeBytearrayExample example);

    ActGeBytearray selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActGeBytearray record, @Param("example") ActGeBytearrayExample example);

    int updateByExampleWithBLOBs(@Param("record") ActGeBytearray record, @Param("example") ActGeBytearrayExample example);

    int updateByExample(@Param("record") ActGeBytearray record, @Param("example") ActGeBytearrayExample example);

    int updateByPrimaryKeySelective(ActGeBytearray record);

    int updateByPrimaryKeyWithBLOBs(ActGeBytearray record);

    int updateByPrimaryKey(ActGeBytearray record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table act_ge_bytearray
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<ActGeBytearray> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table act_ge_bytearray
     *
     * @mbg.generated
     * @author hewei
     */
    ActGeBytearray selectOneByExample(ActGeBytearrayExample example);
}