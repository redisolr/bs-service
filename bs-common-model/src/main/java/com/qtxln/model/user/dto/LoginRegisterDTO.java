package com.qtxln.model.user.dto;

import lombok.Data;

/**
 * @author QT
 * 2018-08-23 17:09
 */
@Data
public class LoginRegisterDTO {
    private String username;

    private String password;

    private String challenge;

    private String gt;

    private String statusKey;

    private String seccode;

    private String validate;
}
