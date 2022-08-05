package com.zw.cloud.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("order")
public interface IOrderService {

    @GetMapping("/order/insert/{userId}/{commodityCode}/{count}/{money}")
    void insert(@PathVariable("userId") String userId, @PathVariable("commodityCode") String commodityCode,
                @PathVariable("count") Integer count, @PathVariable("money") Integer money);

}
