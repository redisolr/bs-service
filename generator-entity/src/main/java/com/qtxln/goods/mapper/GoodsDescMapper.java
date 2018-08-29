package com.qtxln.goods.mapper;

import com.qtxln.goods.entity.GoodsDesc;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsDescMapper {
    int insert(GoodsDesc record);

    int insertSelective(GoodsDesc record);
}