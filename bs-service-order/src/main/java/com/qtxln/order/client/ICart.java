package com.qtxln.order.client;

import com.qtxln.model.user.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author QT
 * 2018-08-28 19:25
 */
@Service
@FeignClient("bs-service-user")
public interface ICart {
    /**
     * 删除购物车中此次生成订单的商品
     *
     * @param cartDTO CartDTO
     * @return String
     */
    @PostMapping(value = "/user/cart/deleteOrderGoods", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String deleteOrderGoods(CartDTO cartDTO);
}
