package com.zw.cloud.tools.base;

import com.zw.cloud.common.utils.MyPermissionCheckException;
import com.zw.cloud.common.utils.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public WebResult handlerException(Exception exception){
        log.error("[GlobalExceptionHandler][handlerException]error is ",exception);
        if(exception instanceof MissingServletRequestParameterException){
            return WebResult.failed()
                    .withErrorCode(WebResult.ErrorCode.PARAMETER_ILLEGAL)
                    .withErrorMsg("缺少必需参数：" +((MissingServletRequestParameterException) exception).getParameterName());
        } else if(exception instanceof MyPermissionCheckException){
            return WebResult.failed()
                    .withErrorCode(WebResult.ErrorCode.METHOD_NOT_ALLOWED)
                    .withErrorMsg("没有操作权限");
        } else if (exception instanceof BindException) {
            BindException bindException = (BindException) exception;
            String msg = bindException.getFieldErrors().stream().map((i) -> String.format("%s(%s):%s", i.getField(), i.getRejectedValue(), i.getDefaultMessage())).collect(Collectors.joining(";"));
            return WebResult.failed().withErrorCode(WebResult.ErrorCode.PARAMETER_ILLEGAL)
                    .withErrorMsg(msg);
        }

        return WebResult.failed()
                .withErrorCode(WebResult.ErrorCode.UNDEFINED)
                .withErrorMsg(exception.getMessage() == null ?
                        WebResult.ErrorCode.UNDEFINED.name() : exception.getMessage());
    }
}
