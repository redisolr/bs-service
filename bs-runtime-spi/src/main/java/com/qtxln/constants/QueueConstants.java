package com.qtxln.constants;

/**
 * @author QT
 * 2018-08-01 9:49
 */
public interface QueueConstants {
    /**
     * goods 消息交换
     */
    String GOODS_EXCHANGE = "goods.exchange";
    /**
     * goods.insert 消息队列名称
     */
    String GOODS_INSERT_QUEUE = "goods.insert.queue";
    /**
     * goods.update 消息队列名称
     */
    String GOODS_UPDATE_QUEUE = "goods.update.queue";
    /**
     * goods.update 消息队列名称
     */
    String GOODS_DELETE_QUEUE = "goods.delete.queue";
    /**
     * 消息路由键
     */
    String GOODS_INSERT_ROUTE_KEY = "goods.insert";
    /**
     * 消息路由键
     */
    String GOODS_UPDATE_ROUTE_KEY = "goods.update";
    /**
     * 消息路由键
     */
    String GOODS_DELETE_ROUTE_KEY = "goods.delete";
}
