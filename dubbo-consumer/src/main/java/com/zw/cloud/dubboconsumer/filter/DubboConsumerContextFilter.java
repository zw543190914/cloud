package com.zw.cloud.dubboconsumer.filter;


import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

@Activate(group = "CONSUMER")
public class DubboConsumerContextFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = null;
        setAttachment(invoker,invocation);
        try {
            //执行业务逻辑
            result = invoker.invoke(invocation);
        }catch(RpcException e) {
            throw e;
        }finally {
            //清理
            RpcContext.getContext().clearAttachments();
        }
        return result;
    }

    private void setAttachment(Invoker<?> invoker,Invocation invocation) {
        try {
            RpcContext.getContext()
                    .setInvoker(invoker)
                    .setInvocation(invocation)
                    .setAttachment("key","value");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
