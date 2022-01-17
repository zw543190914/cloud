package com.zw.cloud.tools.base;

import cn.hutool.http.HttpStatus;
import com.zw.cloud.common.utils.MyPermissionCheckException;
import com.zw.cloud.common.utils.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public WebResult handlerException(Exception exception, HttpServletRequest request){
        if (Objects.nonNull(request)) {
            log.error("[GlobalExceptionHandler][handlerException]uri is {},error is ",request.getRequestURI(),exception);
        } else {
            log.error("[GlobalExceptionHandler][handlerException] ",exception);
        }

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

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public WebResult bindException(BindException e,HttpServletRequest request) {
        if (Objects.nonNull(request)) {
            log.error("[GlobalExceptionHandler][bindException]uri is {},error is ",request.getRequestURI(),e);
        } else {
            log.error("[GlobalExceptionHandler][bindException] error is ",e);
        }
        //String errorMsg = e.getFieldErrors().stream().map((i) -> String.format("%s(%s):%s", i.getField(), i.getRejectedValue(), i.getDefaultMessage())).collect(Collectors.joining(";"));
        String errorMsg = e.getFieldErrors().stream().map((i) -> String.format("%s:%s", i.getField(), i.getDefaultMessage())).collect(Collectors.joining(";"));
        return WebResult.failed().withErrorCode(BAD_REQUEST.value()).withErrorMsg(errorMsg);
    }
}
