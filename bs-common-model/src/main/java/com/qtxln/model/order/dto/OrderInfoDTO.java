package com.qtxln.model.order.dto;

import com.qtxln.model.order.OrderGoods;
import com.qtxln.model.order.OrderShipping;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author QT
 * 2018-08-29 12:33
 */
@Data
public class OrderInfoDTO {
    private Long id;

    private Long userId;

    private String orderNum;

    private BigDecimal payment;

    private Integer status;

    private Date paymentTime;

    private Date consignTime;

    private Date closeTime;

    private Date finishTime;

    private Date gmtCreate;

    private OrderShipping orderShipping;

    private List<OrderGoods> orderGoodsList;
}
