package com.qtxln.model.manager.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author QT
 * 2018-09-05 13:36
 */
@Data
public class PanelContentDTO {
    private Long id;

    private Long panelId;

    private Integer sort;

    private String pictureUrl;

    private String jumpUrl;

    private Long goodsId;

    private String goodsName;

    private BigDecimal goodsPrice;

    private Integer goodsNum;

    private String goodsBarcode;

    private String goodsImage;
}
