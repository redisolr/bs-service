package com.qtxln.manager.mapper;

import com.qtxln.manager.entity.PanelType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PanelTypeMapper {
    int insert(PanelType record);

    int insertSelective(PanelType record);
}