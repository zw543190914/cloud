package com.zw.cloud.dubboprovider.filter;

import com.zw.cloud.dubboprovider.base.ThreadContext;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Activate(group = "PROVIDER")
public class DubboProviderContextFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(DubboProviderContextFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        ThreadLocal<String> workIdThreadLocal = ThreadContext.getWorkIdThreadLocal();
        try {
            getAttachment(invoker,invocation,workIdThreadLocal);
            //执行业务逻辑
            return invoker.invoke(invocation);
        } catch (RpcException e) {
            logger.error("[DubboProviderContextFilter][invoke] error is ",e);
            throw e;
        } finally {
            //清理
            RpcContext.getContext().clearAttachments();
            workIdThreadLocal.remove();
        }
    }

    private void getAttachment(Invoker<?> invoker, Invocation invocation,ThreadLocal<String> workIdThreadLocal) {
        try {
            Map<String, Object> objectAttachments = RpcContext.getContext().
                    setInvoker(invoker).setInvocation(invocation).getObjectAttachments();
            String workId = (String) objectAttachments.get("workId");
            workIdThreadLocal.set(workId);
            logger.info("[DubboProviderContextFilter][invoke]workId is {}",workId);
        }catch(Exception e) {
            logger.error("[DubboProviderContextFilter][invoke]getAttachment error is ",e);
            throw e;
        }
    }

}