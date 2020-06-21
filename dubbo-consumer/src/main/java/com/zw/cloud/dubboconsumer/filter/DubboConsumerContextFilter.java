/*
package com.zw.cloud.dubboconsumer.filter;


import com.zw.cloud.dubboconsumer.base.ThreadContext;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Activate(group = "CONSUMER")
public class DubboConsumerContextFilter implements Filter {

    @Autowired
    private ThreadContext threadContext;

    private Logger logger = LoggerFactory.getLogger(DubboConsumerContextFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            setAttachment(invoker,invocation);
            //执行业务逻辑
            return invoker.invoke(invocation);
        }catch(RpcException e) {
            logger.error("[DubboConsumerContextFilter][invoke] error is {}",e);
            throw e;
        }finally {
            //清理
            RpcContext.getContext().clearAttachments();
        }
    }

    private void setAttachment(Invoker<?> invoker,Invocation invocation) {
        try {
            ThreadLocal<String> workIdThreadLocal = threadContext.getWorkIdThreadLocal();
            String workId = workIdThreadLocal.get();
            RpcContext.getContext()
                    .setInvoker(invoker)
                    .setInvocation(invocation)
                    .setAttachment("workId","0001");
        }catch(Exception e) {
            logger.error("[DubboConsumerContextFilter][invoke]setAttachment error is {}",e);
            throw e;
        }
    }

}
*/
