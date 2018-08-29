package com.qtxln.util;

import com.github.pagehelper.PageInfo;
import com.qtxln.transport.PageData;

/**
 * @author QT
 * 2018-07-24 19:03
 */
public final class PageDataUtil {
    public static <T> PageData toPageData(PageInfo<T> pageInfo) {
        PageData pageData = new PageData();
        pageData.setTotal(pageInfo.getTotal());
        pageData.setPageSize(pageInfo.getPageSize());
        pageData.setPageNum(pageInfo.getPageNum());
        pageData.setData(pageInfo.getList().toArray());
        return pageData;
    }

}
