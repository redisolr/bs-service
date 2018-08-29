package com.qtxln.user.mapper;

import com.qtxln.model.user.Admin;
import com.qtxln.model.user.dto.AdminDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QT
 * 2018-08-14 11:00
 */
@Mapper
@Repository
public interface AdminMapper {
    /**
     * 根据id查询管理员
     *
     * @param id Long
     * @return Admin
     */
    @Select("SELECT id,username,phone,user_head,enable,role_id FROM u_admin WHERE id = #{id}")
    Admin findById(Long id);

    /**
     * 根据用户名查询管理员
     *
     * @param username 管理员名称
     * @return Admin
     */
    @Select("SELECT id,username,password,phone,user_head,enable,role_id FROM u_admin WHERE username = #{username}")
    Admin findByName(String username);

    /**
     * 根据管理员id查询角色id
     *
     * @param id 管理员id
     * @return Long
     */
    @Select("SELECT role_id FROM u_admin WHERE id = #{id}")
    Long findRoleByUserId(Long id);

    /**
     * 添加管理员
     *
     * @param admin Admin
     */
    @Insert("INSERT INTO u_admin(username,password,phone,enable,role_id,gmt_create) VALUES (#{username},#{password},#{phone},#{enable},#{roleId},NOW())")
    void insert(Admin admin);

    /**
     * 查询管理员列表
     *
     * @return List
     */
    @Select("SELECT a.id,a.username,a.phone,a.enable,a.gmt_create,r.name role_name FROM u_admin a left JOIN u_role r on a.role_id = r.id")
    List<AdminDTO> selectAll();

    /**
     * 更新用户启用/禁用状态
     *
     * @param admin Admin
     */
    @Update("UPDATE u_admin SET enable=#{enable} WHERE id=#{id}")
    void updateEnableState(Admin admin);

    /**
     * 编辑管理员
     *
     * @param admin Admin
     */
    void update(Admin admin);

    /**
     * 删除管理员
     * @param id Long
     */
    @Delete("DELETE FROM u_admin WHERE id = #{id}")
    void deleteById(Long id);
}
