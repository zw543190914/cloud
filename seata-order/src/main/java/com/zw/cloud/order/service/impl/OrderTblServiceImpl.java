package com.zw.cloud.order.service.impl;

import com.zw.cloud.order.entity.OrderTbl;
import com.zw.cloud.order.mapper.OrderTblMapper;
import com.zw.cloud.order.service.api.IOrderTblService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zw
 * @since 2022-08-05
 */
@Service
public class OrderTblServiceImpl extends ServiceImpl<OrderTblMapper, OrderTbl> implements IOrderTblService {

    @Override
    @GlobalTransactional
    public void insert(String userId,String commodityCode,Integer count, Integer money){
        OrderTbl order = new OrderTbl();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(money);
        order.setUpdateTime(LocalDateTime.now());
        baseMapper.insert(order);
    }
}
