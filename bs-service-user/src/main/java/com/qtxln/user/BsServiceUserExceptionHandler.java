package com.qtxln.user;

import com.qtxln.exception.BsUserException;
import com.qtxln.transport.BsException;
import com.qtxln.transport.InvokerResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author QT
 * 2018-08-14 11:15
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class BsServiceUserExceptionHandler {
    @ExceptionHandler(Exception.class)
    public InvokerResult handleException(Exception e) {
        if (e instanceof BsException) {
            BsException bs = (BsException) e;
            return bs.toInvokerResult();
        } else {
            e.printStackTrace();
            log.warn("未处理异常:{}", e);
            return new BsUserException(BsUserException.ErrorUserEnum.ERR_EXCEPTION).toInvokerResult();
        }
    }
}
