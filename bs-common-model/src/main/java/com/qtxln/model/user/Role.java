package com.qtxln.model.user;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Long id;

    private String name;

    private Date gmtCreate;

    private Date gmtUpdate;
}