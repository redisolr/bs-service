package com.qtxln.model.order.dto;

import com.qtxln.model.order.OrderGoods;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author QT
 * 2018-08-28 18:33
 */
@Data
public class OrderDTO {
    private Long userId;

    private String username;

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

    private BigDecimal payment;

    private List<OrderGoods> orderGoodsList;
}
