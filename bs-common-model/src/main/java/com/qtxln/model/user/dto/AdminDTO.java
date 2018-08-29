package com.qtxln.model.user.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author QT
 * 2018-08-14 11:23
 */
@Data
public class AdminDTO {
    private Long id;

    private String username;

    private String phone;

    private String userHead;

    private String token;

    private String roleName;

    private Boolean enable;

    private Date gmtCreate;
}
