package com.qtxln.goods.mapper;

import com.qtxln.goods.entity.GoodsClass;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsClassMapper {
    int insert(GoodsClass record);

    int insertSelective(GoodsClass record);
}