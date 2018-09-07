package com.qtxln.model.manager;

import lombok.Data;

import java.util.Date;

@Data
public class PanelContent {
    private Long id;

    private Long panelId;

    private Integer sort;

    private String pictureUrl;

    private String jumpUrl;

    private Long goodsId;

    private Date gmtCreate;

    private Date gmtUpdate;
}