package com.qtxln.model.manager;

import lombok.Data;

import java.util.Date;

@Data
public class PanelType {
    private Long id;

    private String typeName;

    private Integer maxPicture;

    private String typeImage;

    private Date gmtCreate;

    private Date gmtUpdate;
}