package com.qtxln.exception;

import com.qtxln.transport.BsException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author QT
 * 2018-08-14 11:06
 */
public class BsUserException extends BsException {

    public BsUserException(BsUserException.ErrorUserEnum err) {
        super(err.getStatus(), err.getDescription());
    }

    public enum ErrorUserEnum {
        ERR_EXCEPTION(USER_SERVICE, "未知错误异常"),
        USERNAME_NON_EXISTENT(USER_SERVICE + 1, "用户名不存在"),
        USERNAME_OR_PASSWORD_ERR(USER_SERVICE + 2, "用户名或密码错误"),
        USER_DISABLE(USER_SERVICE + 3, "用户已被禁用"),
        VERIFICATION_CODE_ERR(USER_SERVICE + 4, "验证码异常"),
        USERNAME_EXISTENT(USER_SERVICE + 5, "用户名已存在"),
        TOKEN_EXPIRED(USER_SERVICE + 6, "token已过期");


        @Getter
        @Setter
        private Integer status;

        @Getter
        @Setter
        private String description;


        ErrorUserEnum(Integer status, String description) {
            this.status = status;
            this.description = description;
        }
    }
}
