package com.zw.cloud.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zw.cloud.account.entity.AccountTbl;
import com.zw.cloud.account.mapper.AccountTblMapper;
import com.zw.cloud.account.service.api.IAccountTblTccService;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zw
 * @since 2022-08-05
 */
@Service
@Slf4j
public class AccountTblTccServiceImpl extends ServiceImpl<AccountTblMapper, AccountTbl> implements IAccountTblTccService {

    @Override
    @GlobalTransactional
    public void update(String userId,Integer money){
        log.info("[AccountTblTccServiceImpl][update]修改账户");
        baseMapper.deductionMoneyByUserId(userId, money);
        //throw new RuntimeException("test");
    }

    @Override
    public boolean commitTcc(BusinessActionContext context) {
        log.info("[AccountTblTccServiceImpl][commitTcc]xid = {},提交成功", context.getXid());

        return true;
    }

    @Override
    public boolean cancel(BusinessActionContext context) {
        log.info("[AccountTblTccServiceImpl][cancel]xid = {},进行回滚操作",context.getXid());
        // 获取下单时的提交参数
        String userId = context.getActionContext("userId").toString();
        int money = Integer.parseInt(context.getActionContext("money").toString());
        log.info("[OrderTblTccServiceImpl][cancel]xid = {},userId = {},money = {}",context.getXid(),userId,money);
        // 进行分支数据 事务回滚
        // 将扣减数据 加回去
        baseMapper.increaseMoneyByUserId(userId,money);
        return true;
    }

}
