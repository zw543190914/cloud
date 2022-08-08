package com.zw.cloud.order.controller;


import com.zw.cloud.order.service.api.IOrderTblService;
import com.zw.cloud.order.service.api.IOrderTblTccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/order")
public class OrderTblController {

    @Autowired
    private IOrderTblService orderService;

    @Autowired
    private IOrderTblTccService orderTblTccService;

    @GetMapping("/insert/{userId}/{commodityCode}/{count}/{money}")
    //http://localhost:8040/order
    public void insert(@PathVariable String userId, @PathVariable String commodityCode,
                       @PathVariable Integer count, @PathVariable Integer money){
        orderService.insert(userId, commodityCode, count, money);
    }

    @GetMapping("/insertTcc/{userId}/{commodityCode}/{count}/{money}")
    //http://localhost:8040/order
    public void insertTcc(@PathVariable String userId, @PathVariable String commodityCode,
                       @PathVariable Integer count, @PathVariable Integer money){
        orderTblTccService.insert(userId, commodityCode, count, money);
    }
}
