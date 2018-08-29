package com.qtxln.model.user.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author QT
 * 2018-08-27 11:02
 */
@Data
public class CartGoodsDTO {

    private Integer purchaseNumber;

    private Long userId;

    private Long goodsId;

    private String goodsName;

    private Integer goodsNum;

    private BigDecimal goodsPrice;

    private String goodsImage;

    private Boolean checked;
}
