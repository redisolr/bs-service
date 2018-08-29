package com.qtxln.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qtxln.model.goods.Goods;
import com.qtxln.model.goods.dto.GoodsDTO;
import com.qtxln.model.user.dto.CartDTO;
import com.qtxln.model.user.dto.CartGoodsDTO;
import com.qtxln.transport.InvokerResult;
import com.qtxln.user.client.IGoodsClient;
import com.qtxln.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author QT
 * 2018-08-27 10:49
 */
@Service
@Slf4j
public class CartService {
    private static final String CART_PRE = "cart_";
    private final StringRedisTemplate stringRedisTemplate;
    private final IGoodsClient goodsClient;

    @Autowired
    public CartService(StringRedisTemplate stringRedisTemplate, IGoodsClient goodsClient) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.goodsClient = goodsClient;
    }

    public InvokerResult addCart(CartDTO cartDTO) throws IOException {
        String key = CART_PRE + cartDTO.getUserId();
        String hashKey = cartDTO.getGoodsId().toString();
        HashOperations<String, String, String> stringObjectObjectHashOperations = stringRedisTemplate.opsForHash();
        Boolean hasKey = stringObjectObjectHashOperations.hasKey(key, hashKey);
        // 判断redis中是否存在该key
        String value;
        if (hasKey) {
            value = stringObjectObjectHashOperations.get(key, hashKey);
            if (value != null) {
                CartGoodsDTO cartGoodsDTO = JsonUtil.jsonToObj(value, CartGoodsDTO.class);
                cartGoodsDTO.setPurchaseNumber(cartGoodsDTO.getPurchaseNumber() + cartDTO.getPurchaseNumber());
                stringObjectObjectHashOperations.put(key, hashKey, JsonUtil.objToJson(cartGoodsDTO));
            }
        } else {
            CartGoodsDTO cartGoodsDTO = new CartGoodsDTO();
            // 如果不存在,根据商品id取商品信息
            Goods goods = JsonUtil.jsonToObj(goodsClient.getGoods(cartDTO.getGoodsId()), Goods.class);
            BeanUtils.copyProperties(goods, cartGoodsDTO);
            cartGoodsDTO.setPurchaseNumber(cartDTO.getPurchaseNumber());
            cartGoodsDTO.setChecked(true);
            cartGoodsDTO.setUserId(cartDTO.getUserId());
            cartGoodsDTO.setGoodsId(goods.getId());
            stringObjectObjectHashOperations.put(key, hashKey, JsonUtil.objToJson(cartGoodsDTO));
        }
        return InvokerResult.getInstance();
    }

    public InvokerResult getCart(long userId) {
        HashOperations<String, String, String> stringObjectObjectHashOperations = stringRedisTemplate.opsForHash();
        List<CartGoodsDTO> list = new ArrayList<>();
        Map<String, String> goodsDTOMap = stringObjectObjectHashOperations.entries(CART_PRE + userId);
        goodsDTOMap.forEach((key, value) -> {
            try {
                list.add(JsonUtil.jsonToObj(value, CartGoodsDTO.class));
            } catch (IOException e) {
                log.error("序列化异常:{}", e.getMessage());
            }
        });
        return InvokerResult.getInstance(list);
    }

    public InvokerResult updatePurchaseNumber(CartDTO cartDTO) throws IOException {
        String key = CART_PRE + cartDTO.getUserId();
        String hashKey = cartDTO.getGoodsId().toString();
        HashOperations<String, String, String> stringObjectObjectHashOperations = stringRedisTemplate.opsForHash();
        String value = stringObjectObjectHashOperations.get(key, hashKey);
        if (value != null) {
            CartGoodsDTO cartGoodsDTO = JsonUtil.jsonToObj(value, CartGoodsDTO.class);
            cartGoodsDTO.setPurchaseNumber(cartDTO.getPurchaseNumber());
            cartGoodsDTO.setChecked(cartDTO.getChecked());
            stringObjectObjectHashOperations.put(key, hashKey, JsonUtil.objToJson(cartGoodsDTO));
        }
        return InvokerResult.getInstance();
    }

    public InvokerResult checkedAll(CartDTO cartDTO) {
        String key = CART_PRE + cartDTO.getUserId();
        HashOperations<String, String, String> stringObjectObjectHashOperations = stringRedisTemplate.opsForHash();
        Map<String, String> goodsDTOMap = stringObjectObjectHashOperations.entries(key);
        goodsDTOMap.forEach((hashKey, value) -> {
                    try {
                        CartGoodsDTO cartGoodsDTO = JsonUtil.jsonToObj(value, CartGoodsDTO.class);
                        cartGoodsDTO.setChecked(cartDTO.getChecked());
                        stringObjectObjectHashOperations.put(key, hashKey, JsonUtil.objToJson(cartGoodsDTO));
                    } catch (IOException e) {
                        log.error("序列化异常:{}", e.getMessage());
                    }
                }
        );
        return InvokerResult.getInstance();
    }

    public InvokerResult deleteCartGoods(long userId, Long goodsId) {
        HashOperations<String, String, String> stringObjectObjectHashOperations = stringRedisTemplate.opsForHash();
        stringObjectObjectHashOperations.delete(CART_PRE + userId, goodsId.toString());
        return InvokerResult.getInstance();
    }

    public InvokerResult deleteChecked(long userId) {
        HashOperations<String, String, String> stringObjectObjectHashOperations = stringRedisTemplate.opsForHash();
        Map<String, String> goodsDTOMap = stringObjectObjectHashOperations.entries(CART_PRE + userId);
        goodsDTOMap.forEach((hashKey, value) -> {
                    try {
                        CartGoodsDTO cartGoodsDTO = JsonUtil.jsonToObj(value, CartGoodsDTO.class);
                        if (cartGoodsDTO.getChecked()) {
                            stringObjectObjectHashOperations.delete(CART_PRE + userId, hashKey);
                        }
                    } catch (IOException e) {
                        log.error("序列化异常:{}", e.getMessage());
                    }
                }
        );
        return InvokerResult.getInstance();
    }

    public InvokerResult deleteOrderGoods(CartDTO cartDTO) {
        HashOperations<String, String, String> stringObjectObjectHashOperations = stringRedisTemplate.opsForHash();
        cartDTO.getGoodsIdList().forEach(aLong -> {
            stringObjectObjectHashOperations.delete(CART_PRE + cartDTO.getUserId(), aLong.toString());
        });
        return InvokerResult.getInstance();
    }
}
