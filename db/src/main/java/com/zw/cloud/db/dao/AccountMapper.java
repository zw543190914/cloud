package com.zw.cloud.db.dao;

import com.zw.cloud.db.entity.Account;
import com.zw.cloud.db.entity.AccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface AccountMapper {
    long countByExample(AccountExample example);

    int deleteByExample(AccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    @Update("update account set money = money - ${money} where user_id = ${userId}")
    int updateByUserId(@Param("userId") String userId,@Param("money") Integer money);

    int insertSelective(Account record);

    List<Account> selectByExample(AccountExample example);

    Account selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table account
     *
     * @mbg.generated
     * @author hewei
     */
    int batchInsert(@Param("list") List<Account> list);

    /**
     * 这是Mybatis Generator拓展插件生成的方法(请勿删除).
     * This method corresponds to the database table account
     *
     * @mbg.generated
     * @author hewei
     */
    Account selectOneByExample(AccountExample example);
}