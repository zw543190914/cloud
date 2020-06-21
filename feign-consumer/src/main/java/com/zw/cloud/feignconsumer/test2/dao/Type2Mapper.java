package com.zw.cloud.feignconsumer.test2.dao;

import com.zw.cloud.feignconsumer.entity.Type2;
import com.zw.cloud.feignconsumer.entity.Type2Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Type2Mapper {
    long countByExample(Type2Example example);

    int deleteByExample(Type2Example example);

    int deleteByPrimaryKey(Long id);

    int insert(Type2 record);

    int insertSelective(Type2 record);

    List<Type2> selectByExample(Type2Example example);

    Type2 selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Type2 record, @Param("example") Type2Example example);

    int updateByExample(@Param("record") Type2 record, @Param("example") Type2Example example);

    int updateByPrimaryKeySelective(Type2 record);

    int updateByPrimaryKey(Type2 record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table type2
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<Type2> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table type2
     *
     * @mbg.generated
     * @author hewei
     */
    Type2 selectOneByExample(Type2Example example);
}