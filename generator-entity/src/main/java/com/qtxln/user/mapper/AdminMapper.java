package com.qtxln.user.mapper;

import com.qtxln.user.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    int insert(Admin record);

    int insertSelective(Admin record);
}