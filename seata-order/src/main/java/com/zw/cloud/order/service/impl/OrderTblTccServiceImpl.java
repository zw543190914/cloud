package com.zw.cloud.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zw.cloud.order.entity.OrderTbl;
import com.zw.cloud.order.mapper.OrderTblMapper;
import com.zw.cloud.order.service.IAccountService;
import com.zw.cloud.order.service.api.IOrderTblTccService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class OrderTblTccServiceImpl  extends ServiceImpl<OrderTblMapper, OrderTbl> implements IOrderTblTccService {

    @Autowired
    IAccountService accountService;

    @Override
    public void insert(String userId, String commodityCode, Integer count, Integer money) {
        log.info("[OrderTblTccServiceImpl][insert]创建订单");
        // 创建订单
        OrderTbl order = new OrderTbl();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(money);
        order.setUpdateTime(LocalDateTime.now());
        baseMapper.insert(order);

        //accountService.updateTcc(userId,money);
    }

    @Override
    public boolean commitTcc(BusinessActionContext context) {
        log.info("[OrderTblTccServiceImpl][commitTcc]xid = {},提交成功", context.getXid());
        return true;
    }

    @Override
    public boolean cancel(BusinessActionContext context) {
        log.info("[OrderTblTccServiceImpl][cancel]xid = {},进行回滚操作",context.getXid());
        // 获取下单时的提交参数
        String userId = context.getActionContext("userId").toString();
        String commodityCode = context.getActionContext("commodityCode").toString();
        int count = Integer.parseInt(context.getActionContext("count").toString());
        log.info("[OrderTblTccServiceImpl][cancel]xid = {},userId = {},commodityCode = {}",context.getXid(),userId,commodityCode);

        // 进行分支数据 事务回滚
        // 将增加的订单删除
        LambdaUpdateWrapper<OrderTbl> updateWrapper = new LambdaUpdateWrapper<OrderTbl>().eq(OrderTbl::getUserId, userId)
                .eq(OrderTbl::getCommodityCode, commodityCode)
                .eq(OrderTbl::getCount, count);
        baseMapper.delete(updateWrapper);

        return true;
    }
}
