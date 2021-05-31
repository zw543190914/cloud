package com.zw.cloud.tools.base;

import com.alibaba.fastjson.JSONObject;
import com.zw.cloud.common.utils.WebResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

@ControllerAdvice
public class ResponseAutoWrapper implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        Class<?> returnType = methodParameter.getMethod().getReturnType();
        String returnTypeName = returnType.getName();
        // true 转换
        return !Objects.equals(returnTypeName,String.class.getName()) && !Objects.equals(returnTypeName,WebResult.class.getName());

    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (Objects.isNull(data)) {
            return WebResult.success();
        }
        if (data instanceof String
                || data instanceof JSONObject
                || data instanceof WebResult
                || data instanceof ResponseEntity){
            return data;
        }
        return WebResult.success().withData(data);
    }
}
