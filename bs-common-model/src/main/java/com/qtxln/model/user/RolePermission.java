package com.qtxln.model.user;

import lombok.Data;

import java.util.Date;

@Data
public class RolePermission {
    private Long id;

    private Long roleId;

    private Long permissionId;

    private Date gmtCreate;
}