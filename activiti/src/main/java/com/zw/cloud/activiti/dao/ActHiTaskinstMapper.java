package com.zw.cloud.activiti.dao;

import com.zw.cloud.activiti.entity.ActHiTaskinst;
import com.zw.cloud.activiti.entity.ActHiTaskinstExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActHiTaskinstMapper {
    long countByExample(ActHiTaskinstExample example);

    int deleteByExample(ActHiTaskinstExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActHiTaskinst record);

    int insertSelective(ActHiTaskinst record);

    List<ActHiTaskinst> selectByExample(ActHiTaskinstExample example);

    ActHiTaskinst selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActHiTaskinst record, @Param("example") ActHiTaskinstExample example);

    int updateByExample(@Param("record") ActHiTaskinst record, @Param("example") ActHiTaskinstExample example);

    int updateByPrimaryKeySelective(ActHiTaskinst record);

    int updateByPrimaryKey(ActHiTaskinst record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table act_hi_taskinst
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<ActHiTaskinst> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table act_hi_taskinst
     *
     * @mbg.generated
     * @author hewei
     */
    ActHiTaskinst selectOneByExample(ActHiTaskinstExample example);
}