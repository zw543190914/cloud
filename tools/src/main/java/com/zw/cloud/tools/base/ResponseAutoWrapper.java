package com.zw.cloud.tools.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zw.cloud.common.annotation.NotNeedResponseAutoWrapper;
import com.zw.cloud.common.utils.WebResult;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import java.util.Objects;

@ControllerAdvice
public class ResponseAutoWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        if (Objects.isNull(methodParameter.getMethod())) {
            return false;
        }
        NotNeedResponseAutoWrapper annotation = methodParameter.getMethod().getAnnotation(NotNeedResponseAutoWrapper.class);
        return Objects.isNull(annotation);
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object data, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (Objects.isNull(data)) {
            return WebResult.success();
        }
        if (data instanceof WebResult || data instanceof ResponseEntity){
            return data;
        }
        if (data instanceof String) {
            ObjectMapper om = new ObjectMapper();
            return om.writeValueAsString(WebResult.build(data));
        }
        return WebResult.build(data);
    }
}
