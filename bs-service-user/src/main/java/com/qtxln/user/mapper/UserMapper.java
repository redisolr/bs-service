package com.qtxln.user.mapper;

import com.qtxln.model.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author QT
 * 2018-08-23 17:03
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 添加用户
     * @param user User
     */
    @Insert("INSERT INTO u_user (username,password,gmt_create) VALUES (#{username},#{password},now())")
    void insert(User user);

    /**
     * 根据用户名查询用户
     * @param username String
     * @return User
     */
    @Select("SELECT id,username,password,user_head,phone,email FROM u_user WHERE username = #{username}")
    User findByUsername(String username);

    /**
     * 根据用户id查询用户
     * @param id Long
     * @return User
     */
    @Select("SELECT id,username,user_head,phone,email FROM u_user WHERE id = #{id}")
    User findById(Long id);
}
