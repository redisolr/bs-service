package com.qtxln.order;

import com.qtxln.exception.BsOrderException;
import com.qtxln.transport.BsException;
import com.qtxln.transport.InvokerResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author QT
 * 2018-08-28 18:21
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class BsServiceOrderExceptionHandler {
    @ExceptionHandler(Exception.class)
    public InvokerResult handleException(Exception e) {
        if (e instanceof BsException) {
            BsException bs = (BsException) e;
            return bs.toInvokerResult();
        } else {
            e.printStackTrace();
            log.warn("未处理异常:{}", e);
            return new BsOrderException(BsOrderException.ErrorOrderEnum.ERR_EXCEPTION).toInvokerResult();
        }
    }
}
