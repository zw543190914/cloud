package com.zw.cloud.account.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.cloud.account.entity.AccountTbl;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zw
 * @since 2022-08-05
 */
@LocalTCC
public interface IAccountTblTccService extends IService<AccountTbl> {

    /**
     * 定义两阶段提交
     * name = 该tcc的bean名称,全局唯一
     * commitMethod = commit 为二阶段确认方法
     * rollbackMethod = rollback 为二阶段取消方法
     * BusinessActionContextParameter注解 传递参数到二阶段中
     */
    @TwoPhaseBusinessAction(name = "update", commitMethod = "commitTcc", rollbackMethod = "cancel")
    void update(@BusinessActionContextParameter(paramName = "userId") String userId,@BusinessActionContextParameter(paramName = "money") Integer money);

    /**
     * 确认方法、可以另命名，但要保证与commitMethod一致
     * context可以传递try方法的参数
     */
    boolean commitTcc(BusinessActionContext context);

    /**
     * 二阶段回滚方法
     */
    boolean cancel(BusinessActionContext context);

}
