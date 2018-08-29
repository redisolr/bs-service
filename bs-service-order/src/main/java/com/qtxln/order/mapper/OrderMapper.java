package com.qtxln.order.mapper;

import com.qtxln.model.order.Order;
import com.qtxln.model.order.dto.OrderInfoDTO;
import com.qtxln.model.order.dto.OrderListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QT
 * 2018-08-28 18:37
 */
@Mapper
@Repository
public interface OrderMapper {

    /**
     * 添加订单
     *
     * @param order Order
     */
    void insert(Order order);

    /**
     * 根据用户id查询订单列表
     *
     * @param userId Long
     * @return List
     */
    @Select("SELECT id,user_id,order_num,payment,status,gmt_create FROM o_order WHERE user_id = #{userId}" +
            " AND status!=7 ORDER BY gmt_create DESC")
    List<OrderListDTO> findByUserId(Long userId);

    /**
     * 根据订单id更新订单状态
     *
     * @param userId Long
     * @param id     Long
     * @param status Integer
     */
    @Update("UPDATE o_order SET status=#{status} WHERE id=#{id} AND user_id = #{userId}")
    void updateOrderStatus(@Param("status") Integer status, @Param("id") Long id, @Param("userId") Long userId);

    /**
     * 根据订单id查询订单
     * @param id Long
     * @return OrderInfoDTO
     */
    @Select("SELECT id,user_id,order_num,payment,status,payment_time,consign_time,close_time,finish_time,gmt_create FROM o_order WHERE id = #{id} AND status!=7")
    OrderInfoDTO findById(Long id);
}
