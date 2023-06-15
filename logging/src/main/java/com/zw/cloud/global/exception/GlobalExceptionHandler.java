package com.zw.cloud.global.exception;

import cn.hutool.http.HttpStatus;
import com.zw.cloud.global.response.wrapper.entity.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final String TRACE_ID = "TRACE_ID";

    @ExceptionHandler(value = Exception.class)
    public WebResult<Object> handlerException(Exception exception, HttpServletRequest request){
        if (Objects.nonNull(request)) {
            log.warn("[GlobalExceptionHandler][handlerException] tid is {},uri is {},error is ",MDC.get(TRACE_ID),request.getRequestURI(),exception);
        } else {
            log.warn("[GlobalExceptionHandler][handlerException] tid is {}，error is",MDC.get(TRACE_ID),exception);
        }

        if(exception instanceof MissingServletRequestParameterException){
            return WebResult.fail(HttpStatus.HTTP_PAYMENT_REQUIRED,"缺少必需参数：" +((MissingServletRequestParameterException) exception).getParameterName());
        }
        if (exception instanceof BizException) {
            return WebResult.fail(HttpStatus.HTTP_INTERNAL_ERROR,exception.getMessage());
        }
        return WebResult.fail(HttpStatus.HTTP_BAD_REQUEST,exception.getMessage() == null ?
                "未知异常" : exception.getMessage());
    }

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public WebResult<Object> bindException(BindException e) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (Objects.nonNull(requestAttributes)) {
            log.warn("[GlobalExceptionHandler][bindException] tid is {},uri is {},error is ",MDC.get(TRACE_ID),requestAttributes.getRequest().getRequestURI(),e);
        } else {
            log.warn("[GlobalExceptionHandler][bindException] tid is {},error is ",MDC.get(TRACE_ID),e);
        }
        //String errorMsg = e.getFieldErrors().stream().map((i) -> String.format("%s(%s):%s", i.getField(), i.getRejectedValue(), i.getDefaultMessage())).collect(Collectors.joining(";"));
        String errorMsg = e.getFieldErrors().stream().map((i) -> String.format("%s:%s", i.getField(), i.getDefaultMessage())).collect(Collectors.joining(";"));
        return WebResult.fail(HttpStatus.HTTP_BAD_REQUEST,errorMsg);

    }

    @ExceptionHandler({IOException.class})
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public WebResult<Object> ioException(IOException e, HttpServletRequest request) {
        log.warn("[GlobalExceptionHandler][ioException],tid is {},uri is {},error is ",request.getRequestURI(),MDC.get(TRACE_ID),e );
        return WebResult.fail(HttpStatus.HTTP_BAD_REQUEST,e.getMessage());

    }

}
