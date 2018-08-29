package com.qtxln.goods.config;

import com.qtxln.constants.QueueConstants;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author QT
 * 2018-08-01 13:43
 */
@Configuration
public class RabbitMqConfiguration {
    @Bean
    public DirectExchange goodsDirectExchange(){
        return new DirectExchange(QueueConstants.GOODS_EXCHANGE);
    }

    @Bean
    public Queue goodsInsertQueue() {
        return QueueBuilder.durable(QueueConstants.GOODS_INSERT_QUEUE)
                .build();
    }

    @Bean
    public Binding goodsInsertBinding() {
        return BindingBuilder.bind(goodsInsertQueue())
                .to(goodsDirectExchange())
                .with(QueueConstants.GOODS_INSERT_ROUTE_KEY);
    }

    @Bean
    public Queue goodsUpdateQueue() {
        return QueueBuilder.durable(QueueConstants.GOODS_UPDATE_QUEUE)
                .build();
    }

    @Bean
    public Binding goodsUpdateBinding() {
        return BindingBuilder.bind(goodsUpdateQueue())
                .to(goodsDirectExchange())
                .with(QueueConstants.GOODS_UPDATE_ROUTE_KEY);
    }

    @Bean
    public Queue goodsDeleteQueue() {
        return QueueBuilder.durable(QueueConstants.GOODS_DELETE_QUEUE)
                .build();
    }

    @Bean
    public Binding goodsDeleteBinding() {
        return BindingBuilder.bind(goodsDeleteQueue())
                .to(goodsDirectExchange())
                .with(QueueConstants.GOODS_DELETE_ROUTE_KEY);
    }
}
