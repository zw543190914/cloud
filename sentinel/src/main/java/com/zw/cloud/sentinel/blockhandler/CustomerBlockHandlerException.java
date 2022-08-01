package com.zw.cloud.sentinel.blockhandler;

import cn.hutool.http.HttpStatus;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zw.cloud.common.utils.WebResult;

public class CustomerBlockHandlerException {

    public static WebResult handlerParamFlowException(BlockException exception) {
        return WebResult.failed().withErrorCode(HttpStatus.HTTP_NOT_ACCEPTABLE)
                .withErrorMsg("当前热点被限流了");
    }

}
