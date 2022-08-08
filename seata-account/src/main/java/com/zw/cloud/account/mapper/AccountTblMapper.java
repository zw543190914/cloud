package com.zw.cloud.account.mapper;

import com.zw.cloud.account.entity.AccountTbl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zw
 * @since 2022-08-05
 */
public interface AccountTblMapper extends BaseMapper<AccountTbl> {

    @Update("update account_tbl set money = money - ${money} where user_id = ${userId}")
    int deductionMoneyByUserId(@Param("userId") String userId, @Param("money") Integer money);

    @Update("update account_tbl set money = money + ${money} where user_id = ${userId}")
    int increaseMoneyByUserId(@Param("userId") String userId, @Param("money") Integer money);
}
