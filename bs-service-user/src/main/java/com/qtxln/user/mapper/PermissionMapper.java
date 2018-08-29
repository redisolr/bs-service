package com.qtxln.user.mapper;

import com.qtxln.model.user.Permission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QT
 * 2018-08-15 15:35
 */
@Repository
@Mapper
public interface PermissionMapper {
    /**
     * 根据ids集合查询权限url
     *
     * @param ids id集合
     * @return List<String> path
     */
    List<String> findPathByIds(List<Long> ids);

    /**
     * 添加权限
     *
     * @param permission Permission
     */
    @Insert("INSERT INTO u_permission(parent_id,name,is_parent,path) VALUES(#{parentId},#{name},#{isParent},#{path})")
    void insert(Permission permission);

    /**
     * 修改权限
     *
     * @param permission Permission
     */
    @Update("UPDATE u_permission SET name = #{name},path = #{path} WHERE id = #{id}")
    void update(Permission permission);

    /**
     * 查询所有权限点
     *
     * @return List
     */
    @Select("SELECT id,name,parent_id,path,is_parent FROM u_permission")
    List<Permission> selectAll();

    /**
     * 更新 是否成为父节点
     *
     * @param id       权限id
     * @param isParent 是否为父分类
     */
    @Update("UPDATE u_permission SET is_parent = #{isParent} WHERE id = #{id}")
    void updateParent(@Param("id") long id, @Param("isParent") int isParent);

    /**
     * 删除权限
     *
     * @param id 权限id
     */
    @Delete("DELETE FROM u_permission WHERE id = #{id}")
    void delete(@Param("id") long id);

    /**
     * 根据父权限id查询 对应的条数
     *
     * @param parentId 父权限id
     * @return int
     */
    @Select("SELECT COUNT(*) FROM u_permission WHERE parent_id = #{parentId}")
    int selectByParentId(@Param("parentId") long parentId);

    /**
     * 根据权限id查询权限
     *
     * @param id 权限id
     * @return Permission
     */
    @Select("SELECT id,name,path FROM u_permission WHERE id = #{id}")
    Permission selectById(@Param("id") long id);
}
