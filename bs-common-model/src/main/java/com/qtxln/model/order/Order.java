package com.qtxln.model.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {
    private Long id;

    private Long userId;

    private String username;

    private String orderNum;

    private BigDecimal payment;

    private Integer paymentType;

    private BigDecimal postfee;

    private Integer status;

    private Date paymentTime;

    private Date consignTime;

    private Date closeTime;

    private Date finishTime;

    private Date gmtCreate;

    private Date gmtUpdate;
}