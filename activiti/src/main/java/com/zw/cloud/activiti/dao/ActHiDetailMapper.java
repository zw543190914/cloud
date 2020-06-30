package com.zw.cloud.activiti.dao;

import com.zw.cloud.activiti.entity.ActHiDetail;
import com.zw.cloud.activiti.entity.ActHiDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActHiDetailMapper {
    long countByExample(ActHiDetailExample example);

    int deleteByExample(ActHiDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActHiDetail record);

    int insertSelective(ActHiDetail record);

    List<ActHiDetail> selectByExample(ActHiDetailExample example);

    ActHiDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActHiDetail record, @Param("example") ActHiDetailExample example);

    int updateByExample(@Param("record") ActHiDetail record, @Param("example") ActHiDetailExample example);

    int updateByPrimaryKeySelective(ActHiDetail record);

    int updateByPrimaryKey(ActHiDetail record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table act_hi_detail
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<ActHiDetail> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table act_hi_detail
     *
     * @mbg.generated
     * @author hewei
     */
    ActHiDetail selectOneByExample(ActHiDetailExample example);
}