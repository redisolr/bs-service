package com.qtxln.user.mapper;

import com.qtxln.user.entity.ReceivingAddress;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReceivingAddressMapper {
    int insert(ReceivingAddress record);

    int insertSelective(ReceivingAddress record);
}