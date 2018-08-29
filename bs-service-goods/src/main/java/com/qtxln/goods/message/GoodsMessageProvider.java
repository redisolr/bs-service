package com.qtxln.goods.message;

import com.qtxln.constants.QueueConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author QT
 * 2018-08-01 14:19
 */
@Component
public class GoodsMessageProvider {
    private final Logger logger = LoggerFactory.getLogger(GoodsMessageProvider.class);
    private final AmqpTemplate amqpTemplate;

    @Autowired
    public GoodsMessageProvider(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @Async
    public void insertGoodsMessage(Long id) {
        logger.info("添加商品,生产消息商品ID:{}", id);
        amqpTemplate.convertAndSend(QueueConstants.GOODS_EXCHANGE, QueueConstants.GOODS_INSERT_ROUTE_KEY, id);
    }

    @Async
    public void updateGoodsMessage(Long id) {
        logger.info("编辑商品,生产消息商品ID:{}", id);
        amqpTemplate.convertAndSend(QueueConstants.GOODS_EXCHANGE, QueueConstants.GOODS_UPDATE_ROUTE_KEY, id);
    }

    @Async
    public void deleteGoodsMessage(Long id) {
        logger.info("删除商品,生产消息商品ID:{}", id);
        amqpTemplate.convertAndSend(QueueConstants.GOODS_EXCHANGE, QueueConstants.GOODS_DELETE_ROUTE_KEY, id);
    }
}
