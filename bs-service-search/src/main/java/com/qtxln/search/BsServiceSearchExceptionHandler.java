package com.qtxln.search;

import com.qtxln.exception.BsSearchException;
import com.qtxln.transport.BsException;
import com.qtxln.transport.InvokerResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author QT
 * 2018-09-05 13:47
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class BsServiceSearchExceptionHandler {
    @ExceptionHandler(Exception.class)
    public InvokerResult handleException(Exception e) {
        if (e instanceof BsException) {
            BsException bs = (BsException) e;
            return bs.toInvokerResult();
        } else {
            e.printStackTrace();
            log.warn("未处理异常:{}", e);
            return new BsSearchException(BsSearchException.ErrorSearchEnum.ERR_EXCEPTION).toInvokerResult();
        }
    }
}
