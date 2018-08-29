package com.qtxln.exception;

import com.qtxln.transport.BsException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author QT
 * 2018-08-10 10:03
 */
public class BsManagerException extends BsException {

    public BsManagerException(ErrorManagerEnum err) {
        super(err.getStatus(), err.getDescription());
    }

    public enum ErrorManagerEnum {
        ERR_EXCEPTION(MANAGER_SERVICE, "未知错误异常");

        @Getter
        @Setter
        private Integer status;

        @Getter
        @Setter
        private String description;

        ErrorManagerEnum(Integer status, String description) {
            this.status = status;
            this.description = description;
        }
    }
}
