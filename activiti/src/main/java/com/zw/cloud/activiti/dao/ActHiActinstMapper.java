package com.zw.cloud.activiti.dao;

import com.zw.cloud.activiti.entity.ActHiActinst;
import com.zw.cloud.activiti.entity.ActHiActinstExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActHiActinstMapper {
    long countByExample(ActHiActinstExample example);

    int deleteByExample(ActHiActinstExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActHiActinst record);

    int insertSelective(ActHiActinst record);

    List<ActHiActinst> selectByExample(ActHiActinstExample example);

    ActHiActinst selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActHiActinst record, @Param("example") ActHiActinstExample example);

    int updateByExample(@Param("record") ActHiActinst record, @Param("example") ActHiActinstExample example);

    int updateByPrimaryKeySelective(ActHiActinst record);

    int updateByPrimaryKey(ActHiActinst record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table act_hi_actinst
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<ActHiActinst> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table act_hi_actinst
     *
     * @mbg.generated
     * @author hewei
     */
    ActHiActinst selectOneByExample(ActHiActinstExample example);
}