package com.qtxln.transport;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author QT
 * 2018-07-24 19:01
 */
@Data
public class PageData implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 页码，从1开始
     */
    private int pageNum;
    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 总数
     */
    private long total;
    /**
     * 数据
     */
    private Object[] data;
}
