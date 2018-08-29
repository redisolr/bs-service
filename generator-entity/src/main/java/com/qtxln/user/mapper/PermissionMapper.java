package com.qtxln.user.mapper;

import com.qtxln.user.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionMapper {
    int insert(Permission record);

    int insertSelective(Permission record);
}