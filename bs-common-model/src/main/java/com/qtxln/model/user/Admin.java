package com.qtxln.model.user;

import lombok.Data;

import java.util.Date;

@Data
public class Admin {
    private Long id;

    private String username;

    private String password;

    private String phone;

    private String userHead;

    private Boolean enable;

    private Long roleId;

    private Date gmtCreate;

    private Date gmtUpdate;
}