package com.zw.cloud.tools.dao;

import com.zw.cloud.tools.entity.Tc;
import com.zw.cloud.tools.entity.TcExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TcMapper {
    long countByExample(TcExample example);

    int deleteByExample(TcExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tc record);

    int insertSelective(Tc record);

    List<Tc> selectByExample(TcExample example);

    Tc selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tc record, @Param("example") TcExample example);

    int updateByExample(@Param("record") Tc record, @Param("example") TcExample example);

    int updateByPrimaryKeySelective(Tc record);

    int updateByPrimaryKey(Tc record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table tc
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<Tc> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table tc
     *
     * @mbg.generated
     * @author hewei
     */
    Tc selectOneByExample(TcExample example);
}