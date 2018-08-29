package com.qtxln.model.goods.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author QT
 * 2018-07-27 10:17
 */
@Data
public class GoodsDTO {

    private Long id;

    private String goodsName;

    private BigDecimal goodsPrice;

    private Integer goodsNum;

    private String goodsBarcode;

    private String goodsImage;

    private Long goodsCid;

    private Byte goodsStatus;

    private String goodsDesc;

    private String className;

    private List<Long> ids;

    private Integer pageNum;

    private Integer pageSize;
}
