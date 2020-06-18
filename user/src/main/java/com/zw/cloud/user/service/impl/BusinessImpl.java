package com.zw.cloud.user.service.impl;

import com.zw.cloud.user.service.IAccountService;
import com.zw.cloud.user.service.IBusinessService;
import com.zw.cloud.user.service.IOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessImpl implements IBusinessService {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IAccountService accountService;

    @Override
    @GlobalTransactional(name = "order")
    public void createOrder(String userId, String commodityCode, Integer count, Integer money) {
        orderService.insert(userId, commodityCode, count, money);
        accountService.update(userId, money);
        //throw new RuntimeException("error");
    }
}
