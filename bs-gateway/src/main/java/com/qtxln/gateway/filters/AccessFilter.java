package com.qtxln.gateway.filters;

import com.qtxln.gateway.service.UserClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author QT
 * 2018-08-10 18:15
 */
//@Component
public class AccessFilter implements GlobalFilter, Ordered {


    private final UserClient userClient;

    private static final String TOKEN_NAME = "Authorization";
    private static final String LOGIN_NAME = "login";

    @Autowired
    public AccessFilter(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (!exchange.getRequest().getPath().value().endsWith(LOGIN_NAME)) {
            String token = exchange.getRequest().getHeaders().getFirst(TOKEN_NAME);
            if (StringUtils.isEmpty(token) || checkTokenIsExpire(token)) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            String path = exchange.getRequest().getPath().value();
            if (checkPermissions(token, path)) {
                exchange.getResponse().setStatusCode(HttpStatus.PROXY_AUTHENTICATION_REQUIRED);
                return exchange.getResponse().setComplete();
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 验证token是否过期
     *
     * @param token token
     * @return boolean
     */
    private boolean checkTokenIsExpire(String token) {
        return Boolean.parseBoolean(userClient.checkTokenIsExpire(token));
    }

    /**
     * 验证权限
     *
     * @param token token
     * @param path  请求路径
     * @return boolean
     */
    private boolean checkPermissions(String token, String path) {
        Map<String, String> param = new HashMap<>(2);
        param.put("token", token);
        param.put("path", path);
        return Boolean.parseBoolean(userClient.checkPermissions(param));
    }
}
