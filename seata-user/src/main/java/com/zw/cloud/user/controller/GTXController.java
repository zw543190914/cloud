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

    @GetMapping("/createOrderAT/{userId}/{commodityCode}/{count}/{money}")
    //http://localhost:8030/user/createOrderAT/1/novel7/2/100
    public void createOrderAT(@PathVariable String userId, @PathVariable String commodityCode,
                            @PathVariable Integer count, @PathVariable Integer money) {
        businessService.createOrderAT(userId, commodityCode, count, money);
    }

    @GetMapping("/createOrderTcc/{userId}/{commodityCode}/{count}/{money}")
    //http://localhost:8030/user/createOrderTcc/1/apple14/2/100
    public void createOrderTcc(@PathVariable String userId, @PathVariable String commodityCode,
                            @PathVariable Integer count, @PathVariable Integer money) {
        businessService.createOrderTcc(userId, commodityCode, count, money);
    }
}
