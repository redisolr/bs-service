package com.qtxln.exception;

import com.qtxln.transport.BsException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author QT
 * 2018-07-24 9:58
 */
public class BsSearchException extends BsException {

    public BsSearchException(ErrorSearchEnum err) {
        super(err.getStatus(), err.getDescription());
    }

    public enum ErrorSearchEnum {
        ERR_EXCEPTION(SEARCH_SERVICE, "未知错误异常"),
        RESULT_NULL(SEARCH_SERVICE+1, "未找到相应的数据");

        @Getter
        @Setter
        private Integer status;

        @Getter
        @Setter
        private String description;

        ErrorSearchEnum(Integer status, String description) {
            this.status = status;
            this.description = description;
        }
    }


}
