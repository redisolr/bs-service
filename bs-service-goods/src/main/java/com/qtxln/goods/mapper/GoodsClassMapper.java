package com.qtxln.goods.mapper;

import com.qtxln.model.goods.GoodsClass;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QT
 * 2018-07-25 11:26
 */
@Mapper
@Repository
public interface GoodsClassMapper {

    @Select("SELECT id, parent_id, class_name, is_parent FROM g_goods_class")
    List<GoodsClass> selectAll();

    @Insert("INSERT INTO g_goods_class(parent_id,class_name,is_parent,gmt_create) VALUES\n" +
            "(#{parentId},#{className},#{isParent},NOW())")
    void insert(GoodsClass goodsClass);

    @Update("UPDATE g_goods_class SET is_parent = #{isParent} WHERE id = #{id}")
    void updateParent(@Param("id") long id, @Param("isParent") int isParent);

    @Delete("DELETE FROM g_goods_class WHERE id = #{id}")
    void delete(@Param("id") long id);

    @Select("SELECT COUNT(*) FROM g_goods_class WHERE parent_id = #{parentId}")
    int selectByParentId(@Param("parentId") long parentId);

    @Select("SELECT class_name FROM g_goods_class WHERE id = #{id}")
    GoodsClass selectById(@Param("id") long id);
}
