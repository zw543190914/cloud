package com.zw.cloud.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("account")
public interface IAccountService {

    @PutMapping("/account/update/{userId}/{money}")
    void update(@PathVariable("userId") String userId, @PathVariable("money") Integer money);

    @PutMapping("/account/updateTcc/{userId}/{money}")
    void updateTcc(@PathVariable("userId") String userId, @PathVariable("money") Integer money);

}
