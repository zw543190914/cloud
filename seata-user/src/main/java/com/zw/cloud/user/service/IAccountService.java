package com.zw.cloud.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("account")
@RequestMapping("/account")
public interface IAccountService {

    @PutMapping("/{userId}/{money}")
    void update(@PathVariable String userId, @PathVariable Integer money);

}
