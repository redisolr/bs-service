package com.qtxln.order.mapper;

import com.qtxln.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    int insert(Order record);

    int insertSelective(Order record);
}