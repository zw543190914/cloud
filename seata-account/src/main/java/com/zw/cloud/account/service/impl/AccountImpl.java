package com.zw.cloud.account.service.impl;

import com.zw.cloud.account.dao.AccountTblMapper;
import com.zw.cloud.account.entity.AccountTbl;
import com.zw.cloud.account.entity.AccountTblExample;
import com.zw.cloud.account.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountImpl implements IAccountService {

    @Autowired
    private AccountTblMapper mapper;

    @Override
    public void insert(AccountTbl account){
        mapper.insert(account);
    }

    @Override
    public void update(String userId,Integer money){
        mapper.updateByUserId(userId, money);
    }

    @Override
    public List<AccountTbl> query(){
        return mapper.selectByExample(new AccountTblExample());
    }


}
