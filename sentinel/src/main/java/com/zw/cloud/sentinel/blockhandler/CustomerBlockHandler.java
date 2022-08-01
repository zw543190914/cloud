package com.zw.cloud.sentinel.blockhandler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zw.cloud.common.utils.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class CustomerBlockHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        WebResult webResult = null;
        log.info("BlockException======="+e.getRule());
        if(e instanceof FlowException){
            webResult = WebResult.failed().withErrorCodeAndMsg(300,"接口被限流了");
        } else if(e instanceof DegradeException){
            webResult = WebResult.failed().withErrorCodeAndMsg(301,"接口被熔断降级了");
        } else if(e instanceof ParamFlowException){
            webResult = WebResult.failed().withErrorCodeAndMsg(302,"接口热点参数限流了");
        } else if(e instanceof SystemBlockException){
            webResult = WebResult.failed().withErrorCodeAndMsg(303,"接口触发系统保护规则了");
        } else if(e instanceof AuthorityException){
            webResult = WebResult.failed().withErrorCodeAndMsg(304,"授权接口不通过");
        } else {
            webResult = WebResult.failed().withErrorCodeAndMsg(500,"服务错误");
        }
        //返回json格式
        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(httpServletResponse.getWriter(),webResult);
    }

}
