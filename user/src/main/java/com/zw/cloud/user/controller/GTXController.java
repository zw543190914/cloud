package com.zw.cloud.user.controller;

import com.zw.cloud.user.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class GTXController {

    @Autowired
    private IBusinessService businessService;

    @GetMapping("/createOrder/{userId}/{commodityCode}/{count}/{money}")
    //http://localhost:8030/user/createOrder/1/1/2/100
    public void createOrder(@PathVariable String userId, @PathVariable String commodityCode,
                            @PathVariable Integer count, @PathVariable Integer money) {
        businessService.createOrder(userId, commodityCode, count, money);
    }
}
