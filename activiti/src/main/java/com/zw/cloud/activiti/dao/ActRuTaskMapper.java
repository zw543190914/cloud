package com.zw.cloud.activiti.dao;

import com.zw.cloud.activiti.entity.ActRuTask;
import com.zw.cloud.activiti.entity.ActRuTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActRuTaskMapper {
    long countByExample(ActRuTaskExample example);

    int deleteByExample(ActRuTaskExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActRuTask record);

    int insertSelective(ActRuTask record);

    List<ActRuTask> selectByExample(ActRuTaskExample example);

    ActRuTask selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActRuTask record, @Param("example") ActRuTaskExample example);

    int updateByExample(@Param("record") ActRuTask record, @Param("example") ActRuTaskExample example);

    int updateByPrimaryKeySelective(ActRuTask record);

    int updateByPrimaryKey(ActRuTask record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table act_ru_task
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<ActRuTask> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table act_ru_task
     *
     * @mbg.generated
     * @author hewei
     */
    ActRuTask selectOneByExample(ActRuTaskExample example);
}