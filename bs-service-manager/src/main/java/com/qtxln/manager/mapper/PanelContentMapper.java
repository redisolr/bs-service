package com.qtxln.manager.mapper;

import com.qtxln.model.manager.PanelContent;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QT
 * 2018-08-06 17:51
 */
@Mapper
@Repository
public interface PanelContentMapper {

    /**
     * 根据板块id查询板块内容
     *
     * @param panelId 板块id
     * @return List
     */
    @Select("SELECT id,sort,picture_url,jump_url,gmt_create FROM m_panel_content WHERE panel_id = #{panelId}")
    List<PanelContent> selectByPanelId(Long panelId);

    /**
     * 添加板块内容
     *
     * @param panelContent PanelContent
     */
    @Insert("INSERT INTO m_panel_content(panel_id,sort,picture_url,jump_url,gmt_create) VALUES" +
            " (#{panelId},#{sort},#{pictureUrl},#{jumpUrl},NOW())")
    void insert(PanelContent panelContent);

    /**
     * 根据id获取板块内容
     *
     * @param id 内容id
     * @return PanelContent
     */
    @Select("SELECT id,sort,picture_url,jump_url FROM m_panel_content WHERE id = #{id}")
    PanelContent getPanelContent(Long id);

    /**
     * 更新板块内容
     *
     * @param panelContent PanelContent
     */
    @Update("UPDATE m_panel_content SET sort = #{sort},picture_url = #{pictureUrl}," +
            "jump_url = #{jumpUrl},gmt_update = now() WHERE id = #{id}")
    void update(PanelContent panelContent);

    /**
     * 删除板块内容
     *
     * @param id 内容id
     */
    @Delete("DELETE FROM m_panel_content WHERE id = #{id}")
    void delete(Long id);
}
