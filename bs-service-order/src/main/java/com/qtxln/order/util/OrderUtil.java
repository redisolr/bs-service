package com.qtxln.order.util;

import com.qtxln.util.DateUtil;

/**
 * @author QT
 * 2018-08-28 18:59
 */
public class OrderUtil {
    public static String generateOrderNum(Long userId) {
        Long milliSecond = DateUtil.milliSecond();
        return milliSecond + String.format("%04d", userId);
    }
}
