package com.qtxln.goods.mapper;

import com.qtxln.model.goods.GoodsDesc;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author QT
 * 2018-07-27 10:11
 */
@Mapper
@Repository
public interface GoodsDescMapper {

    @Insert("INSERT INTO g_goods_desc (goods_id, goods_desc,gmt_create) VALUES (#{goodsId},#{goodsDesc},NOW())")
    void insert(GoodsDesc goodsDesc);

    @Select("SELECT goods_desc FROM g_goods_desc WHERE goods_id = #{goodsId}")
    GoodsDesc selectByGoodsId(@Param("goodsId") long id);

    @Update("UPDATE g_goods_desc SET goods_desc = #{goodsDesc}, gmt_update = NOW() WHERE goods_id = #{goodsId}")
    void updateByGoodsId(GoodsDesc goodsDesc);
}
