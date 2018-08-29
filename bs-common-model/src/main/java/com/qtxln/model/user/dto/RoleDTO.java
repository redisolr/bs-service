package com.qtxln.model.user.dto;

import com.qtxln.model.user.RolePermission;
import lombok.Data;

import java.util.List;

/**
 * @author QT
 * 2018-08-15 15:48
 */
@Data
public class RoleDTO {
    private Long id;
    private String name;
    private List<RolePermission> rolePermissionList;
}
