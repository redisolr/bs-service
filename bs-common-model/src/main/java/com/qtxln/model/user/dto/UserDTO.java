package com.qtxln.model.user.dto;

import lombok.Data;

/**
 * @author QT
 * 2018-08-27 14:59
 */
@Data
public class UserDTO {
    private Long id;

    private String username;

    private String userHead;

    private String phone;

    private String sex;

    private String email;

    private String token;
}
