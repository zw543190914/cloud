package com.zw.cloud.sentinel.blockhandler;

import cn.hutool.http.HttpStatus;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zw.cloud.common.utils.WebResult;

public class CustomerBlockHandler {
    public static WebResult handlerExceptionA(BlockException exception) {
        return WebResult.failed().withErrorCode(HttpStatus.HTTP_NOT_ACCEPTABLE)
                .withErrorMsg("this is handlerExceptionA----1");
    }

    public static WebResult handlerExceptionB(BlockException exception) {
        return WebResult.failed().withErrorCode(HttpStatus.HTTP_NOT_ACCEPTABLE)
                .withErrorMsg("this is handlerExceptionB=====2");
    }
}
