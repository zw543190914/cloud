package com.zw.cloud.order.controller;

import com.zw.cloud.order.entity.OrderTbl;
import com.zw.cloud.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/insert/{userId}/{commodityCode}/{count}/{money}")
    //http://localhost:8040/order
    public void insert(@PathVariable String userId,@PathVariable String commodityCode,
                       @PathVariable Integer count,@PathVariable Integer money){
        orderService.insert(userId, commodityCode, count, money);
    }

    @GetMapping
    //http://localhost:8040/order
    public List<OrderTbl> query(){
        return orderService.query();
    }
}
