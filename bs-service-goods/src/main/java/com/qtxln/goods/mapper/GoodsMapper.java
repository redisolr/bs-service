package com.qtxln.goods.mapper;


import com.qtxln.model.goods.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsMapper {

    @Select("SELECT id, goods_name, goods_price, goods_num, goods_image, goods_barcode, goods_status FROM g_goods WHERE goods_status != 2")
    List<Goods> selectAll();

    void insertSelective(Goods goods);

    @Select("SELECT id, goods_name, goods_price, goods_cid, goods_num, goods_image, goods_barcode, goods_status FROM g_goods WHERE id = #{id}")
    Goods selectById(@Param("id") long id);

    @Update("UPDATE g_goods SET goods_name = #{goodsName}, goods_price = #{goodsPrice}, goods_num = #{goodsNum},\n" +
            "goods_cid = #{goodsCid}, goods_image = #{goodsImage}, goods_status = #{goodsStatus}, gmt_update = NOW()\n" +
            "WHERE id = #{id}")
    void updateById(Goods goods);

    @Delete("UPDATE g_goods SET goods_status = 2 WHERE id = #{id}")
    void deleteById(@Param("id") long id);

    void deleteByIds(List<Long> ids);
}