package com.qtxln.search.message;

import com.qtxln.constants.QueueConstants;
import com.qtxln.search.service.BookGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author QT
 * 2018-08-01 14:46
 */
@Component
@Slf4j
public class GoodsMessageConsumer {
    private final BookGoodsService bookGoodsService;

    @Autowired
    public GoodsMessageConsumer(BookGoodsService bookGoodsService) {
        this.bookGoodsService = bookGoodsService;
    }

    @RabbitListener(queues = QueueConstants.GOODS_INSERT_QUEUE)
    public void insertGoodsListener(Long id) {
        log.info("添加商品,监听商品ID:{}", id);
        bookGoodsService.save(id);
    }

    @RabbitListener(queues = QueueConstants.GOODS_UPDATE_QUEUE)
    public void updateGoodsListener(Long id) {
        log.info("编辑商品,监听商品ID:{}", id);
        bookGoodsService.update(id);
    }

    @RabbitListener(queues = QueueConstants.GOODS_DELETE_QUEUE)
    public void deleteGoodsListener(Long id) {
        log.info("删除商品,监听商品ID:{}", id);
        bookGoodsService.deleteById(id);
    }

}
