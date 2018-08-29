package com.qtxln.model.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderGoods {
    private Long id;

    private Long orderId;

    private Long goodsId;

    private Integer purchaseNumber;

    private BigDecimal goodsPrice;

    private String goodsName;

    private String goodsImage;

    private BigDecimal totalFee;

    private Date gmtCreate;

    private Date gmtUpdate;
}