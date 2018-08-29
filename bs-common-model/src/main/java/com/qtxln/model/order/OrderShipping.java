package com.qtxln.model.order;

import lombok.Data;

import java.util.Date;

@Data
public class OrderShipping {
    private Long id;

    private Long orderId;

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

    private Date gmtCreate;

    private Date gmtUpdate;
}