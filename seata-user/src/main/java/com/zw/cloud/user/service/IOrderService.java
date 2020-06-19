package com.zw.cloud.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("order")
@RequestMapping("/order")
public interface IOrderService {

    @GetMapping("/insert/{userId}/{commodityCode}/{count}/{money}")
    void insert(@PathVariable String userId, @PathVariable String commodityCode,
                @PathVariable Integer count, @PathVariable Integer money);

}
