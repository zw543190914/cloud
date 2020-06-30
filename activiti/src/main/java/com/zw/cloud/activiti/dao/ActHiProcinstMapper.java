package com.zw.cloud.activiti.dao;

import com.zw.cloud.activiti.entity.ActHiProcinst;
import com.zw.cloud.activiti.entity.ActHiProcinstExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActHiProcinstMapper {
    long countByExample(ActHiProcinstExample example);

    int deleteByExample(ActHiProcinstExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActHiProcinst record);

    int insertSelective(ActHiProcinst record);

    List<ActHiProcinst> selectByExample(ActHiProcinstExample example);

    ActHiProcinst selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActHiProcinst record, @Param("example") ActHiProcinstExample example);

    int updateByExample(@Param("record") ActHiProcinst record, @Param("example") ActHiProcinstExample example);

    int updateByPrimaryKeySelective(ActHiProcinst record);

    int updateByPrimaryKey(ActHiProcinst record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table act_hi_procinst
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<ActHiProcinst> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table act_hi_procinst
     *
     * @mbg.generated
     * @author hewei
     */
    ActHiProcinst selectOneByExample(ActHiProcinstExample example);
}