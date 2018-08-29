package com.qtxln.model.user;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;

    private String username;

    private String password;

    private String userHead;

    private String phone;

    private String sex;

    private String email;

    private Boolean enable;

    private Date gmtCreate;

    private Date gmtUpdate;
}