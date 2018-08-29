package com.qtxln.exception;

import com.qtxln.transport.BsException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author QT
 * 2018-07-24 9:58
 */
public class BsGoodsException extends BsException {

    public BsGoodsException(ErrorGoodsEnum err) {
        super(err.getStatus(), err.getDescription());
    }

    public enum ErrorGoodsEnum {
        ERR_EXCEPTION(GOODS_SERVICE, "未知错误异常");

        @Getter
        @Setter
        private Integer status;

        @Getter
        @Setter
        private String description;

        ErrorGoodsEnum(Integer status, String description) {
            this.status = status;
            this.description = description;
        }
    }


}
