package com.zw.cloud.tools.base;

import cn.hutool.http.HttpStatus;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public WebResult handlerException(Exception exception, HttpServletRequest request){
        log.error("[GlobalExceptionHandler][handlerException]uri is {},error is ",request.getRequestURI(),exception);
        if(exception instanceof MissingServletRequestParameterException){
            return WebResult.failed()
                    .withErrorCode(HttpStatus.HTTP_PAYMENT_REQUIRED)
                    .withErrorMsg("缺少必需参数：" +((MissingServletRequestParameterException) exception).getParameterName());
        } else if(exception instanceof MyPermissionCheckException){
            return WebResult.failed()
                    .withErrorCode(HttpStatus.HTTP_FORBIDDEN)
                    .withErrorMsg("没有操作权限");
        }

        return WebResult.failed()
                .withErrorCode(HttpStatus.HTTP_BAD_REQUEST)
                .withErrorMsg(exception.getMessage() == null ?
                        "未知异常" : exception.getMessage());
    }
}
