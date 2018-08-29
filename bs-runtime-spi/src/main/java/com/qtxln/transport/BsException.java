package com.qtxln.transport;

import lombok.Getter;
import lombok.Setter;

/**
 * @author QT
 * 2018-07-23 18:56
 */
public class BsException extends Exception {
    public final static Integer GOODS_SERVICE = 100000;
    public final static Integer USER_SERVICE = 110000;
    public final static Integer ORDER_SERVICE = 120000;
    public final static Integer SEARCH_SERVICE = 130000;
    public final static Integer CART_SERVICE = 140000;
    public final static Integer MANAGER_SERVICE = 150000;

    @Getter
    @Setter
    private Integer status;

    @Getter
    @Setter
    private String message;

    public BsException(Integer status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public BsException(String message) {
        super();
        this.message = message;
    }

    public InvokerResult toInvokerResult() {
        return InvokerResult.getInstance(this.status, this.message);
    }
}
