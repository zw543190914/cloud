package com.zw.cloud.feignprovider02.base;

import com.zw.cloud.common.utils.WebResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

@ControllerAdvice
public class ResponseAutoWrapper extends AbstractMappingJacksonResponseBodyAdvice {
    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue mappingJacksonValue,
                                           MediaType mediaType, MethodParameter methodParameter,
                                           ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        Object data = mappingJacksonValue.getValue();
        if (data instanceof String
                || data instanceof WebResult
                || data instanceof ResponseEntity){
            return;
        }
        mappingJacksonValue.setValue(WebResult.success().withData(data));
    }
}
