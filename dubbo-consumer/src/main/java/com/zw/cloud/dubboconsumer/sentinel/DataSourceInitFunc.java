package com.zw.cloud.dubboconsumer.sentinel;

import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;

import java.util.ArrayList;
import java.util.List;

public class DataSourceInitFunc implements InitFunc {
    @Override
    public void init() throws Exception {
       loadRule();
       registerFallback();
    }

    private void loadRule(){
        List<DegradeRule> degradeRuleList = new ArrayList<>();
        DegradeRule degradeRule = new DegradeRule();
        // 熔断的 服务/方法
        degradeRule.setResource("com.zw.cloud.dubboproviderapi.service.IProviderService");
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        degradeRule.setCount(5);
        // 熔断时长 100s
        degradeRule.setTimeWindow(100);
        degradeRuleList.add(degradeRule);
        DegradeRuleManager.loadRules(degradeRuleList);
    }

    private void registerFallback(){
        DubboAdapterGlobalConfig.setConsumerFallback(new DubboFallback() {
            @Override
            public Result handle(Invoker<?> invoker, Invocation invocation, BlockException e) {
                AsyncRpcResult result = new AsyncRpcResult(null,invocation);
                result.setException(e.getCause());
                return result;
            }
        });
    }
}
