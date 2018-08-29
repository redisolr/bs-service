package com.qtxln.user.mapper;

import com.qtxln.model.user.RolePermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QT
 * 2018-08-15 15:35
 */
@Repository
@Mapper
public interface RolePermissionMapper {

    /**
     * 批量添加角色权限对应关系
     *
     * @param list List<RolePermission>
     */
    void insertBatch(List<RolePermission> list);

    /**
     * 根据角色id删除角色权限对应关系
     *
     * @param roleId 角色id
     */
    @Delete("DELETE FROM u_role_permission WHERE role_id = #{roleId}")
    void deleteByRoleId(Long roleId);

    /**
     * 根据角色id查询角色权限对应关系
     *
     * @param roleId 角色id
     * @return List<RolePermission>
     */
    @Select("SELECT permission_id FROM u_role_permission WHERE role_id = #{roleId}")
    List<RolePermission> findByRoleId(Long roleId);
}
