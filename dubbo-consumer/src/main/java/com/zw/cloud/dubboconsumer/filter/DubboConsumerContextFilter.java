package com.zw.cloud.dubboconsumer.filter;


import com.zw.cloud.dubboconsumer.base.ThreadContext;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Activate(group = "CONSUMER")
public class DubboConsumerContextFilter implements Filter {

    @Autowired
    private ThreadContext threadContext;

    private Logger logger = LoggerFactory.getLogger(DubboConsumerContextFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            Map<String,String> attachments = invocation.getAttachments();
            String workId = attachments.get("workId");
            logger.info("[DubboConsumerContextFilter][invoke]workId is {}",workId);

            //执行业务逻辑
            return invoker.invoke(invocation);
        }catch(RpcException e) {
            logger.error("[DubboConsumerContextFilter][invoke] error is ",e);
            throw e;
        }finally {
            //清理
            RpcContext.getContext().clearAttachments();
        }
    }

    private void getAttachment(Invoker<?> invoker,Invocation invocation) {
        try {
            String workId = RpcContext.getContext()
                    .setInvoker(invoker)
                    .setInvocation(invocation)
                    .getAttachment("workId");
        }catch(Exception e) {
            logger.error("[DubboConsumerContextFilter][invoke]setAttachment error is ",e);
            throw e;
        }
    }

}
