package com.zw.cloud.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RefreshScope
public class GatewayFilter  implements Ordered, GlobalFilter {

    @Value("${spring.environment}")
    private String environment;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 这里可以获取 请求对象 和 响应对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        log.info("[GatewayFilter][filter] uri is {},environment is {}",request.getPath(),environment);

        // 逻辑处理


        // 逻辑处理

        // 传递给下一级别的过滤器
        return chain.filter(exchange);
    }

    /**
     * 这里的返回值决定拦截器的优先级，数字越小越先被触发，支持负数
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
