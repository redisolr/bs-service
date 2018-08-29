package com.qtxln.model.goods;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsClass {
    private Long id;

    private Long parentId;

    private String className;

    private Boolean isParent;

    private Date gmtCreate;

    private Date gmtUpdate;
}