package com.qtxln.model.order.dto;

import com.qtxln.model.order.OrderGoods;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author QT
 * 2018-08-29 10:39
 */
@Data
public class OrderListDTO {
    private Long id;

    private Long userId;

    private String orderNum;

    private BigDecimal payment;

    private Integer status;

    private Date gmtCreate;

    private List<OrderGoods> orderGoodsList;
}
