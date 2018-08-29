package com.qtxln.goods;

import com.qtxln.exception.BsGoodsException;
import com.qtxln.transport.BsException;
import com.qtxln.transport.InvokerResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.ResourceAccessException;

/**
 * @author QT
 * 2018-07-24 12:50
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class BsServiceGoodsExceptionHandler {
    @ExceptionHandler(Exception.class)
    public InvokerResult handleException(Exception e) {
        if (e instanceof BsException) {
            BsException bs = (BsException)e;
            return bs.toInvokerResult();
        }else {
            e.printStackTrace();
            log.warn("未处理异常:{}",e);
            // 检测是否服务还未完全启动的异常
            if (e instanceof IllegalStateException) {
                if (e.getMessage().contains("No instances available for ")) {
                    return new BsException(e.getMessage()).toInvokerResult();
                }
            }else if (e instanceof MissingServletRequestParameterException) {
                //缺少必要参数
                return new BsException(e.getMessage()).toInvokerResult();
            }else if (e instanceof HttpRequestMethodNotSupportedException) {
                //请求方式不对
                return new BsException("请求方式不正确").toInvokerResult();
            }else if (e instanceof ResourceAccessException) {
                //资源请求失败
                return new BsException("资源请求失败，请稍候").toInvokerResult();
            }
            return new BsGoodsException(BsGoodsException.ErrorGoodsEnum.ERR_EXCEPTION).toInvokerResult();
        }
    }
}
