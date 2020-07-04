package com.zw.cloud.shiro.dao;

import com.zw.cloud.shiro.entity.RoleResource;
import com.zw.cloud.shiro.entity.RoleResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleResourceMapper {
    long countByExample(RoleResourceExample example);

    int deleteByExample(RoleResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    List<RoleResource> selectByExample(RoleResourceExample example);

    RoleResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleResource record, @Param("example") RoleResourceExample example);

    int updateByExample(@Param("record") RoleResource record, @Param("example") RoleResourceExample example);

    int updateByPrimaryKeySelective(RoleResource record);

    int updateByPrimaryKey(RoleResource record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table role_resource
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<RoleResource> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table role_resource
     *
     * @mbg.generated
     * @author hewei
     */
    RoleResource selectOneByExample(RoleResourceExample example);
}