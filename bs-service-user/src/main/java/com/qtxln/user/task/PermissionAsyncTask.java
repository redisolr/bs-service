package com.qtxln.user.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qtxln.model.user.RolePermission;
import com.qtxln.user.mapper.PermissionMapper;
import com.qtxln.user.mapper.RolePermissionMapper;
import com.qtxln.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author QT
 * 2018-08-15 16:31
 */
@Component
@Slf4j
public class PermissionAsyncTask {
    private final RedisTemplate<String, String> redisTemplate;
    private final RolePermissionMapper rolePermissionMapper;
    private final PermissionMapper permissionMapper;

    @Autowired
    public PermissionAsyncTask(RedisTemplate<String, String> redisTemplate, RolePermissionMapper rolePermissionMapper,
                               PermissionMapper permissionMapper) {
        this.redisTemplate = redisTemplate;
        this.rolePermissionMapper = rolePermissionMapper;
        this.permissionMapper = permissionMapper;
    }

    @Async
    public void insertPermissionTask(Long roleId) {
        List<RolePermission> rolePermissionList = rolePermissionMapper.findByRoleId(roleId);
        List<Long> permissionIds = new ArrayList<>();
        rolePermissionList.forEach(rolePermission -> permissionIds.add(rolePermission.getPermissionId()));
        List<String> pathList = permissionMapper.findPathByIds(permissionIds);
        try {
            redisTemplate.opsForValue().set(roleId.toString(), JsonUtil.objToJson(pathList));
        } catch (JsonProcessingException e) {
            log.error("序列化异常");
        }
    }

    @Async
    public void deletePermissionTask(Long roleId) {
        redisTemplate.opsForValue().set(roleId.toString(), "", 1, TimeUnit.SECONDS);
    }
}
