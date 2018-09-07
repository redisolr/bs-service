package com.qtxln.manager.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author QT
 * 2018-09-05 13:59
 */
@Service
@FeignClient("bs-service-goods")
public interface IGoodsClient {
    /**
     * 根据商品id获取商品信息
     *
     * @param id long
     * @return InvokerResult
     */
    @GetMapping("/goods/goods/getById")
    String getGoods(@RequestParam("id") long id);
}
