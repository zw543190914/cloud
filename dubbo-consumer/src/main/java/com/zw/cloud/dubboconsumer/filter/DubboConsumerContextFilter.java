package com.zw.cloud.dubboconsumer.filter;


import com.zw.cloud.dubboconsumer.base.ThreadContext;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Activate(group = "CONSUMER")
public class DubboConsumerContextFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(DubboConsumerContextFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        ThreadLocal<String> workIdThreadLocal = ThreadContext.getWorkIdThreadLocal();
        try {
            // 注入用户信息
            RpcContext.getContext().getObjectAttachments().put("workId", workIdThreadLocal.get());
            logger.info("[DubboConsumerContextFilter][invoke]workId is {}",workIdThreadLocal.get());

            //执行远程调用
            return invoker.invoke(invocation);
        }catch(RpcException e) {
            logger.error("[DubboConsumerContextFilter][invoke] error is ",e);
            throw e;
        }finally {
            //清理
            RpcContext.getContext().clearAttachments();
            workIdThreadLocal.remove();
        }
    }

}
