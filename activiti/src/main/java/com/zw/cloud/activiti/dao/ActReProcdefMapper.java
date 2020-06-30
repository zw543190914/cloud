package com.zw.cloud.activiti.dao;

import com.zw.cloud.activiti.entity.ActReProcdef;
import com.zw.cloud.activiti.entity.ActReProcdefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActReProcdefMapper {
    long countByExample(ActReProcdefExample example);

    int deleteByExample(ActReProcdefExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActReProcdef record);

    int insertSelective(ActReProcdef record);

    List<ActReProcdef> selectByExample(ActReProcdefExample example);

    ActReProcdef selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActReProcdef record, @Param("example") ActReProcdefExample example);

    int updateByExample(@Param("record") ActReProcdef record, @Param("example") ActReProcdefExample example);

    int updateByPrimaryKeySelective(ActReProcdef record);

    int updateByPrimaryKey(ActReProcdef record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table act_re_procdef
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<ActReProcdef> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table act_re_procdef
     *
     * @mbg.generated
     * @author hewei
     */
    ActReProcdef selectOneByExample(ActReProcdefExample example);
}