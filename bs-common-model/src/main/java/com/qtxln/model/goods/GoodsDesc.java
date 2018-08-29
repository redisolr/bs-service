package com.qtxln.model.goods;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsDesc {
    private Long id;

    private Long goodsId;

    private Date gmtCreate;

    private Date gmtUpdate;

    private String goodsDesc;
}