package com.qtxln.transport;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author QT
 * 2018-07-23 18:37
 */
@Data
@AllArgsConstructor
public class InvokerResult implements Serializable {

    public static InvokerResult getInstance(Integer status, Object result, String message) {
        return new InvokerResult(status, result, message);
    }

    public static InvokerResult getInstance(Object result) {
        return new InvokerResult(0, result, null);
    }

    public static InvokerResult getInstance(Integer status, String message) {
        return new InvokerResult(status, null, message);
    }

    public static InvokerResult getInstance() {
        return new InvokerResult(0, null, "调用服务成功");
    }

    private Integer status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object result;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
}
