package com.zw.cloud.feignconsumer.test.dao;

import com.zw.cloud.feignconsumer.entity.Type;
import com.zw.cloud.feignconsumer.entity.TypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TypeMapper {
    long countByExample(TypeExample example);

    int deleteByExample(TypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Type record);

    int insertSelective(Type record);

    List<Type> selectByExample(TypeExample example);

    Type selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Type record, @Param("example") TypeExample example);

    int updateByExample(@Param("record") Type record, @Param("example") TypeExample example);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table type
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<Type> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table type
     *
     * @mbg.generated
     * @author hewei
     */
    Type selectOneByExample(TypeExample example);
}