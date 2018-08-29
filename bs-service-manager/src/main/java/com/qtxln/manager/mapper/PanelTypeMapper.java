package com.qtxln.manager.mapper;

import com.qtxln.model.manager.PanelType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QT
 * 2018-08-06 17:55
 */
@Mapper
@Repository
public interface PanelTypeMapper {

    /**
     * 添加板块类型
     *
     * @param panelType PanelType
     */
    @Insert("INSERT INTO m_panel_type(type_name,max_picture,type_image,gmt_create) VALUES (#{typeName},#{maxPicture},#{typeImage},NOW())")
    void insert(PanelType panelType);

    /**
     * 查询所有
     *
     * @return List
     */
    @Select("SELECT id, type_name, max_picture, type_image FROM m_panel_type")
    List<PanelType> selectAll();
}