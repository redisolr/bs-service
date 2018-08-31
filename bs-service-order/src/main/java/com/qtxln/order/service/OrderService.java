package com.qtxln.order.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtxln.model.order.Order;
import com.qtxln.model.order.OrderGoods;
import com.qtxln.model.order.OrderShipping;
import com.qtxln.model.order.dto.OrderDTO;
import com.qtxln.model.order.dto.OrderInfoDTO;
import com.qtxln.model.order.dto.OrderListDTO;
import com.qtxln.model.user.dto.CartDTO;
import com.qtxln.order.client.ICart;
import com.qtxln.order.constants.OrderConstants;
import com.qtxln.order.mapper.OrderGoodsMapper;
import com.qtxln.order.mapper.OrderMapper;
import com.qtxln.order.mapper.OrderShippingMapper;
import com.qtxln.order.util.OrderUtil;
import com.qtxln.transport.InvokerResult;
import com.qtxln.util.PageDataUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author QT
 * 2018-08-28 18:37
 */
@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderShippingMapper orderShippingMapper;
    private final OrderGoodsMapper orderGoodsMapper;
    private final ICart cart;

    @Autowired
    public OrderService(OrderMapper orderMapper, OrderShippingMapper orderShippingMapper,
                        OrderGoodsMapper orderGoodsMapper, ICart cart) {
        this.orderMapper = orderMapper;
        this.orderShippingMapper = orderShippingMapper;
        this.orderGoodsMapper = orderGoodsMapper;
        this.cart = cart;
    }

    @Transactional(rollbackFor = Exception.class)
    public InvokerResult insert(OrderDTO orderDTO) {
        // 添加订单基本信息
        Order order = new Order();
        order.setUserId(orderDTO.getUserId());
        order.setUsername(orderDTO.getUsername());
        order.setOrderNum(OrderUtil.generateOrderNum(orderDTO.getUserId()));
        order.setPayment(orderDTO.getPayment());
        orderMapper.insert(order);

        //物流信息
        OrderShipping orderShipping = new OrderShipping();
        BeanUtils.copyProperties(orderDTO, orderShipping);
        orderShipping.setOrderId(order.getId());
        orderShippingMapper.insert(orderShipping);

        //添加订单商品信息
        List<Long> list = new ArrayList<>();
        orderDTO.getOrderGoodsList().forEach(orderGoods -> {
            list.add(orderGoods.getGoodsId());
            orderGoods.setOrderId(order.getId());
            orderGoods.setTotalFee(orderGoods.getGoodsPrice().multiply(BigDecimal.valueOf(orderGoods.getPurchaseNumber())));
        });
        orderGoodsMapper.insertBatch(orderDTO.getOrderGoodsList());

        //删除购物车中的数据
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserId(orderDTO.getUserId());
        cartDTO.setGoodsIdList(list);
        cart.deleteOrderGoods(cartDTO);
        return InvokerResult.getInstance(order.getId());
    }

    public InvokerResult list(Long userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderListDTO> orderList = orderMapper.findByUserId(userId);
        orderList.forEach(orderListDTO -> {
            List<OrderGoods> orderGoods = orderGoodsMapper.findByOrderId(orderListDTO.getId());
            orderListDTO.setOrderGoodsList(orderGoods);
        });
        return InvokerResult.getInstance(PageDataUtil.toPageData(new PageInfo<>(orderList)));
    }

    public InvokerResult delete(Long id) {
        orderMapper.updateOrderStatus(OrderConstants.ORDER_DELETE, id);
        return InvokerResult.getInstance();
    }

    public InvokerResult get(Long id) {
        OrderInfoDTO orderInfoDTO = orderMapper.findById(id);
        OrderShipping orderShipping = orderShippingMapper.findByOrderId(id);
        orderInfoDTO.setOrderShipping(orderShipping);
        List<OrderGoods> orderGoods = orderGoodsMapper.findByOrderId(id);
        orderInfoDTO.setOrderGoodsList(orderGoods);
        return InvokerResult.getInstance(orderInfoDTO);
    }

    public InvokerResult cancel(Long id) {
        orderMapper.updateOrderStatus(OrderConstants.ORDER_CANCEL, id);
        return InvokerResult.getInstance();
    }

    public InvokerResult deliver(Long id) {
        orderMapper.updateOrderStatus(OrderConstants.DELIVER_GOODS, id);
        return InvokerResult.getInstance();
    }

    public InvokerResult findAll(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Order> list = orderMapper.findAll();
        return InvokerResult.getInstance(PageDataUtil.toPageData(new PageInfo<>(list)));
    }
}
