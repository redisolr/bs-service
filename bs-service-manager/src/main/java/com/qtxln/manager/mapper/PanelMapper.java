package com.qtxln.manager.mapper;

import com.qtxln.model.manager.Panel;
import com.qtxln.model.manager.dto.PanelDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QT
 * 2018-08-06 17:53
 */
@Mapper
@Repository
public interface PanelMapper {
    /**
     * 添加板块
     *
     * @param panel Panel
     */
    @Insert("INSERT INTO m_panel(panel_name,type_id,sort,status,remark,gmt_create) VALUES" +
            " (#{panelName},#{typeId},#{sort},#{status},#{remark},NOW())")
    void insert(Panel panel);

    /**
     * 查询所有
     *
     * @return List
     */
    @Select("SELECT p.id,p.panel_name,pt.id type_id,pt.type_name,p.sort,p.status,p.remark,pt.max_picture FROM m_panel p " +
            "LEFT JOIN m_panel_type pt ON p.type_id = pt.id ORDER BY p.sort")
    List<PanelDTO> selectAll();
}