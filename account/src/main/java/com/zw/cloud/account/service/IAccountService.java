package com.zw.cloud.account.service;

import com.zw.cloud.account.entity.AccountTbl;

import java.util.List;

public interface IAccountService {

    void insert(AccountTbl account);

    void update(String userId,Integer money);

    List<AccountTbl> query();
}
