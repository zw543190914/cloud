package com.zw.cloud.account.controller;


import com.zw.cloud.account.service.api.IAccountTblService;
import com.zw.cloud.account.service.api.IAccountTblTccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zw
 * @since 2022-08-05
 */
@RestController
@RequestMapping("/account")
public class AccountTblController {

    @Autowired
    private IAccountTblService accountService;
    @Autowired
    private IAccountTblTccService accountTblTccService;

    @PutMapping("/update/{userId}/{money}")
    //http://localhost:8050/account/1/100
    public void update(@PathVariable String userId, @PathVariable Integer money){
        accountService.update(userId, money);
    }

    @PutMapping("/updateTcc/{userId}/{money}")
    //http://localhost:8050/account/1/100
    public void updateTcc(@PathVariable String userId, @PathVariable Integer money){
        accountTblTccService.update(userId, money);
    }
}
