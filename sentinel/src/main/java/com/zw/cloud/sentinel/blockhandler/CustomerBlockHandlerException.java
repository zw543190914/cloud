package com.zw.cloud.sentinel.blockhandler;

import cn.hutool.http.HttpStatus;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zw.cloud.global.response.wrapper.entity.WebResult;

public class CustomerBlockHandlerException {

    public static WebResult handlerParamFlowException(BlockException exception) {
        return WebResult.fail(HttpStatus.HTTP_NOT_ACCEPTABLE,"当前热点被限流了");
    }

}
