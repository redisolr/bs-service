package com.qtxln.order.mapper;

import com.qtxln.order.entity.OrderShipping;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderShippingMapper {
    int insert(OrderShipping record);

    int insertSelective(OrderShipping record);
}