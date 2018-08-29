package com.qtxln.goods.mapper;

import com.qtxln.goods.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsMapper {
    int insert(Goods record);

    int insertSelective(Goods record);
}