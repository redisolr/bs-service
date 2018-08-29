package com.qtxln.order.mapper;

import com.qtxln.model.order.OrderGoods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author QT
 * 2018-08-28 18:44
 */
@Mapper
@Repository
public interface OrderGoodsMapper {
    /**
     * 添加订单商品信息 OrderGoods
     * @param orderGoods OrderGoods
     */
    @Insert("INSERT INTO o_order_goods (order_id,goods_id,purchase_number,goods_price,goods_name,goods_image,total_fee,gmt_create) VALUES" +
            " (#{orderId},#{goodsId},#{purchaseNumber},#{goodsPrice},#{goodsName},#{goodsImage},#{totalFee},now())")
    void insert(OrderGoods orderGoods);

    /**
     * 批量添加
     * @param orderGoodsList List
     */
    void insertBatch(List<OrderGoods> orderGoodsList);

    /**
     * 根据订单id查询订单商品
     * @param orderId Long
     * @return List
     */
    @Select("SELECT goods_id,purchase_number,goods_price,goods_name,goods_image,total_fee FROM o_order_goods WHERE order_id = #{orderId}")
    List<OrderGoods> findByOrderId(Long orderId);
}
