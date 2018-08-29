package com.qtxln.exception;

import com.qtxln.transport.BsException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author QT
 * 2018-08-28 18:22
 */
public class BsOrderException extends BsException {

    public BsOrderException(BsOrderException.ErrorOrderEnum err) {
        super(err.getStatus(), err.getDescription());
    }


    public enum ErrorOrderEnum {
        ERR_EXCEPTION(MANAGER_SERVICE, "未知错误异常");

        @Getter
        @Setter
        private Integer status;

        @Getter
        @Setter
        private String description;

        ErrorOrderEnum(Integer status, String description) {
            this.status = status;
            this.description = description;
        }
    }
}
