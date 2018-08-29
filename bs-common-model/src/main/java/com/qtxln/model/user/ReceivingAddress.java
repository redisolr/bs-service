package com.qtxln.model.user;

import lombok.Data;

import java.util.Date;

@Data
public class ReceivingAddress {
    private Long id;

    private Long userId;

    private String contactName;

    private String contactPhone;

    private String addressDetail;

    private Boolean isDefault;

    private Date gmtCreate;

    private Date gmtUpdate;
}