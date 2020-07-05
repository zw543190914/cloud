package com.zw.cloud.tools.base;

import com.zw.cloud.common.utils.MyPermissionCheckException;
import com.zw.cloud.common.utils.WebResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public WebResult handlerException(Exception exception){
        if(exception instanceof MissingServletRequestParameterException){
            return WebResult.failed()
                    .withErrorCode(WebResult.ErrorCode.PARAMETER_ILLEGAL)
                    .withErrorMsg("缺少必需参数：" +((MissingServletRequestParameterException) exception).getParameterName());
        } else if(exception instanceof MyPermissionCheckException){
            return WebResult.failed()
                    .withErrorCode(WebResult.ErrorCode.METHOD_NOT_ALLOWED)
                    .withErrorMsg("没有操作权限");
        }

        return WebResult.failed()
                .withErrorCode(WebResult.ErrorCode.UNDEFINED)
                .withErrorMsg(exception.getMessage() == null ?
                        WebResult.ErrorCode.UNDEFINED.name() : exception.getMessage());
    }
}
