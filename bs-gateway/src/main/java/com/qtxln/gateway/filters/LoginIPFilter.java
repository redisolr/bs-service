package com.qtxln.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author QT
 * 2018-08-10 18:30
 */
public class LoginIPFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        System.out.println(Objects.requireNonNull(request.getRemoteAddress()).getHostName());
        System.out.println(request.getRemoteAddress().getHostString());

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }

}
