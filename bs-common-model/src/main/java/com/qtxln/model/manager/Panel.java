package com.qtxln.model.manager;

import lombok.Data;

import java.util.Date;

@Data
public class Panel {
    private Long id;

    private String panelName;

    private Long typeId;

    private Long sort;

    private Byte status;

    private String remark;

    private Date gmtCreate;

    private Date gmtUpdate;
}