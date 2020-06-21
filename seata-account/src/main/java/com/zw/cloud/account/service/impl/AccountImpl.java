package com.zw.cloud.account.service.impl;

import com.zw.cloud.account.dao.AccountTblMapper;
import com.zw.cloud.account.entity.AccountTbl;
import com.zw.cloud.account.entity.AccountTblExample;
import com.zw.cloud.account.service.IAccountService;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service
public class AccountImpl implements IAccountService {

    @Autowired
    private AccountTblMapper mapper;

    private Logger logger = LoggerFactory.getLogger(AccountImpl.class);


    @Override
    public void insert(AccountTbl account){
        mapper.insert(account);
    }

    @Override
    @GlobalTransactional
    public void update(String userId,Integer money){
        try {
            mapper.updateByUserId(userId, money);
        } catch (Exception e) {
            logger.error("[AccountImpl][update] error is {}",e);
            throw e;
        }
    }

    @Override
    public List<AccountTbl> query(){
        return mapper.selectByExample(new AccountTblExample());
    }


}
