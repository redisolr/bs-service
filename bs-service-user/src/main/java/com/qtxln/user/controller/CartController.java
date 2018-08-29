package com.qtxln.user.controller;

import com.qtxln.constants.AuthConstants;
import com.qtxln.exception.BsUserException;
import com.qtxln.model.user.dto.CartDTO;
import com.qtxln.transport.InvokerResult;
import com.qtxln.user.service.CartService;
import com.qtxln.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author QT
 * 2018-08-27 10:48
 */
@RestController
@RequestMapping("cart")
public class CartController {
    private final CartService cartService;
    private final HttpServletRequest request;

    @Autowired
    public CartController(CartService cartService, HttpServletRequest request) {
        this.cartService = cartService;
        this.request = request;
    }

    @PostMapping("add")
    public InvokerResult addCart(@RequestBody CartDTO cartDTO) throws IOException, BsUserException {
        Long userId = TokenUtil.getUserId(request.getHeader(AuthConstants.TOKEN));
        cartDTO.setUserId(userId);
        return cartService.addCart(cartDTO);
    }

    @GetMapping("get")
    public InvokerResult getCart() throws BsUserException {
        Long userId = TokenUtil.getUserId(request.getHeader(AuthConstants.TOKEN));
        return cartService.getCart(userId);
    }

    @PutMapping("updatePurchaseNumber")
    public InvokerResult updatePurchaseNumber(@RequestBody CartDTO cartDTO) throws BsUserException, IOException {
        Long userId = TokenUtil.getUserId(request.getHeader(AuthConstants.TOKEN));
        cartDTO.setUserId(userId);
        return cartService.updatePurchaseNumber(cartDTO);
    }

    @PutMapping("checkedAll")
    public InvokerResult checkedAll(@RequestBody CartDTO cartDTO) throws BsUserException {
        Long userId = TokenUtil.getUserId(request.getHeader(AuthConstants.TOKEN));
        cartDTO.setUserId(userId);
        return cartService.checkedAll(cartDTO);
    }

    @DeleteMapping("delete")
    public InvokerResult deleteCartGoods(@RequestParam("goodsId") Long goodsId) throws BsUserException {
        Long userId = TokenUtil.getUserId(request.getHeader(AuthConstants.TOKEN));
        return cartService.deleteCartGoods(userId, goodsId);
    }

    @DeleteMapping("deleteChecked")
    public InvokerResult deleteChecked() throws BsUserException {
        Long userId = TokenUtil.getUserId(request.getHeader(AuthConstants.TOKEN));
        return cartService.deleteChecked(userId);
    }

    @PostMapping("deleteOrderGoods")
    public InvokerResult deleteOrderGoods(@RequestBody CartDTO cartDTO) throws IOException, BsUserException {
        return cartService.deleteOrderGoods(cartDTO);
    }
}
