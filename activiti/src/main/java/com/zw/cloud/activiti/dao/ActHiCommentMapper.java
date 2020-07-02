package com.zw.cloud.activiti.dao;

import com.zw.cloud.activiti.entity.ActHiComment;
import com.zw.cloud.activiti.entity.ActHiCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActHiCommentMapper {
    long countByExample(ActHiCommentExample example);

    int deleteByExample(ActHiCommentExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActHiComment record);

    int insertSelective(ActHiComment record);

    List<ActHiComment> selectByExampleWithBLOBs(ActHiCommentExample example);

    List<ActHiComment> selectByExample(ActHiCommentExample example);

    ActHiComment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActHiComment record, @Param("example") ActHiCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") ActHiComment record, @Param("example") ActHiCommentExample example);

    int updateByExample(@Param("record") ActHiComment record, @Param("example") ActHiCommentExample example);

    int updateByPrimaryKeySelective(ActHiComment record);

    int updateByPrimaryKeyWithBLOBs(ActHiComment record);

    int updateByPrimaryKey(ActHiComment record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table act_hi_comment
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<ActHiComment> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table act_hi_comment
     *
     * @mbg.generated
     * @author hewei
     */
    ActHiComment selectOneByExample(ActHiCommentExample example);
}