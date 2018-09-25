package com.qtxln.user.mapper;

import com.qtxln.model.user.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QT
 * 2018-08-14 11:28
 */
@Mapper
@Repository
public interface RoleMapper {
    /**
     * 添加角色
     *
     * @param role 角色实体
     */
    void insert(Role role);

    /**
     * 编辑角色
     * @param role Role
     */
    @Update("UPDATE u_role SET name=#{name}, gmt_update=NOW() WHERE id=#{id}")
    void update(Role role);

    /**
     * 根据id删除角色
     *
     * @param id 角色id
     */
    @Delete("DELETE FROM u_role WHERE id = #{id}")
    void deleteById(Long id);

    /**
     * 查询所有角色
     * @return List
     */
    @Select("SELECT id,name,gmt_create FROM u_role")
    List<Role> findAll();

    /**
     * 根据角色id查询角色详情
     * @param id Long
     * @return Role
     */
    @Select("SELECT id,name,gmt_create FROM u_role WHERE id = #{id}")
    Role findById(Long id);

}
