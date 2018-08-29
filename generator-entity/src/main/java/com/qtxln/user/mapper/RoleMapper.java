package com.qtxln.user.mapper;

import com.qtxln.user.entity.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);
}