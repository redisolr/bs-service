package com.qtxln.model.user.dto;

import lombok.Data;

import java.util.List;

/**
 * @author QT
 * 2018-08-27 10:47
 */
@Data
public class CartDTO {
    private Long userId;

    private Long goodsId;

    private Integer purchaseNumber;

    private Boolean checked;

    private List<Long> goodsIdList;
}
