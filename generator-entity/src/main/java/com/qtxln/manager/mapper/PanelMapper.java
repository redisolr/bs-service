package com.qtxln.manager.mapper;

import com.qtxln.manager.entity.Panel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PanelMapper {
    int insert(Panel record);

    int insertSelective(Panel record);
}