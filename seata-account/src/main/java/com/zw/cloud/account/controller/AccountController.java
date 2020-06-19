package com.zw.cloud.account.controller;

import com.zw.cloud.account.entity.AccountTbl;
import com.zw.cloud.account.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping
    //http://localhost:8050/account
    public void insert(@RequestBody AccountTbl account){

        accountService.insert(account);
    }

    @PutMapping("/{userId}/{money}")
    //http://localhost:8050/account/1/100
    public void update(@PathVariable String userId,@PathVariable Integer money){
        accountService.update(userId, money);
    }

    @GetMapping
    //http://localhost:8050/account
    public List<AccountTbl> query(){
        return accountService.query();
    }
}
