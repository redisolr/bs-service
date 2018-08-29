package com.qtxln.model.goods;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Goods {
    private Long id;

    private String goodsName;

    private BigDecimal goodsPrice;

    private Integer goodsNum;

    private String goodsBarcode;

    private String goodsImage;

    private Long goodsCid;

    private Byte goodsStatus;

    private Date gmtCreate;

    private Date gmtUpdate;
}