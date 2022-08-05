package com.zw.cloud.account.service.impl;

import com.zw.cloud.account.entity.AccountTbl;
import com.zw.cloud.account.mapper.AccountTblMapper;
import com.zw.cloud.account.service.api.IAccountTblService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zw
 * @since 2022-08-05
 */
@Service
public class AccountTblServiceImpl extends ServiceImpl<AccountTblMapper, AccountTbl> implements IAccountTblService {

    @Override
    @GlobalTransactional
    public void update(String userId,Integer money){
        baseMapper.updateByUserId(userId, money);
    }

}
