package com.zw.cloud.activiti.dao;

import com.zw.cloud.activiti.entity.ActReDeployment;
import com.zw.cloud.activiti.entity.ActReDeploymentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActReDeploymentMapper {
    long countByExample(ActReDeploymentExample example);

    int deleteByExample(ActReDeploymentExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActReDeployment record);

    int insertSelective(ActReDeployment record);

    List<ActReDeployment> selectByExample(ActReDeploymentExample example);

    ActReDeployment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActReDeployment record, @Param("example") ActReDeploymentExample example);

    int updateByExample(@Param("record") ActReDeployment record, @Param("example") ActReDeploymentExample example);

    int updateByPrimaryKeySelective(ActReDeployment record);

    int updateByPrimaryKey(ActReDeployment record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table act_re_deployment
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<ActReDeployment> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table act_re_deployment
     *
     * @mbg.generated
     * @author hewei
     */
    ActReDeployment selectOneByExample(ActReDeploymentExample example);
}