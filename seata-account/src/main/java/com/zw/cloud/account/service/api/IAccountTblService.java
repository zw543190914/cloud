package com.zw.cloud.account.service.api;

import com.zw.cloud.account.entity.AccountTbl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zw
 * @since 2022-08-05
 */
public interface IAccountTblService extends IService<AccountTbl> {

    void update(String userId,Integer money);
}
