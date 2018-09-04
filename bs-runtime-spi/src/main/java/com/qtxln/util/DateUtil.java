package com.qtxln.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author QT
 * 2018-08-14 17:39
 */
public class DateUtil {
    public static final long ONE_DAY_TIME = 1000 * 60 * 60 * 24;

    public static Date getDate() {
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getDate(long seconds) {
        return Date.from(LocalDateTime.now().plusSeconds(seconds).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }

    public static Long milliSecond() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}
