package com.zw.cloud.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("account")
public interface IAccountService {

    @PutMapping("/account/{userId}/{money}")
    void update(@PathVariable("userId") String userId, @PathVariable("money") Integer money);

}
