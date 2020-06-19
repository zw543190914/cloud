package com.zw.cloud.account.dao;

import com.zw.cloud.account.entity.AccountTbl;
import com.zw.cloud.account.entity.AccountTblExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AccountTblMapper {
    long countByExample(AccountTblExample example);

    int deleteByExample(AccountTblExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountTbl record);

    @Update("update account_tbl set money = money - ${money} where user_id = ${userId}")
    int updateByUserId(String userId,Integer money);

    int insertSelective(AccountTbl record);

    List<AccountTbl> selectByExample(AccountTblExample example);

    AccountTbl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountTbl record, @Param("example") AccountTblExample example);

    int updateByExample(@Param("record") AccountTbl record, @Param("example") AccountTblExample example);

    int updateByPrimaryKeySelective(AccountTbl record);

    int updateByPrimaryKey(AccountTbl record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table account_tbl
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<AccountTbl> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table account_tbl
     *
     * @mbg.generated
     * @author hewei
     */
    AccountTbl selectOneByExample(AccountTblExample example);
}