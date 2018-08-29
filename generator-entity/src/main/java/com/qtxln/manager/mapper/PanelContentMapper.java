package com.qtxln.manager.mapper;

import com.qtxln.manager.entity.PanelContent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PanelContentMapper {
    int insert(PanelContent record);

    int insertSelective(PanelContent record);
}