package com.qtxln.order.mapper;

import com.qtxln.model.order.OrderShipping;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author QT
 * 2018-08-28 18:42
 */
@Mapper
@Repository
public interface OrderShippingMapper {
    /**
     * 添加物流信息
     * @param orderShipping OrderShipping
     */
    @Insert("INSERT INTO o_order_shipping (order_id,receiver_name,receiver_phone,receiver_address,gmt_create) VALUES" +
            " (#{orderId},#{receiverName},#{receiverPhone},#{receiverAddress},now())")
    void insert(OrderShipping orderShipping);

    /**
     * 根据订单id查询物流信息
     * @param orderId Long
     * @return OrderShipping
     */
    @Select("SELECT receiver_name,receiver_phone,receiver_address FROM o_order_shipping WHERE order_id = #{orderId} ")
    OrderShipping findByOrderId(Long orderId);
}
