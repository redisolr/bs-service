package com.qtxln.gateway.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @author QT
 * 2018-08-15 14:53
 */
@Service
@FeignClient("bs-service-user")
public interface UserClient {
    /**
     * 校验token是否过期
     * @param token
     * @return
     */
    @GetMapping("/user/admin/checkTokenIsExpire/{token}")
    String checkTokenIsExpire(@PathVariable("token") String token);
    /**
     * 验证权限
     *
     * @param map
     * @return
     */
    @PostMapping(value = "/user/admin/checkPermissions", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String checkPermissions(Map<String, String> map);
}
