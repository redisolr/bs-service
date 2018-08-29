package com.qtxln.order.controller;

import com.qtxln.constants.AuthConstants;
import com.qtxln.exception.BsUserException;
import com.qtxln.model.order.dto.OrderDTO;
import com.qtxln.order.service.OrderService;
import com.qtxln.transport.InvokerResult;
import com.qtxln.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author QT
 * 2018-08-28 18:37
 */
@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;
    private final HttpServletRequest request;

    @Autowired
    public OrderController(OrderService orderService, HttpServletRequest request) {
        this.orderService = orderService;
        this.request = request;
    }

    @PostMapping("insert")
    public InvokerResult insert(@RequestBody OrderDTO orderDTO) throws BsUserException {
        Long userId = TokenUtil.getUserId(request.getHeader(AuthConstants.TOKEN));
        orderDTO.setUserId(userId);
        return orderService.insert(orderDTO);
    }

    @GetMapping("list")
    public InvokerResult list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) throws BsUserException {
        Long userId = TokenUtil.getUserId(request.getHeader(AuthConstants.TOKEN));
        return orderService.list(userId, pageNum, pageSize);
    }

    @DeleteMapping("delete")
    public InvokerResult delete(@RequestParam("id") Long id) throws BsUserException {
        Long userId = TokenUtil.getUserId(request.getHeader(AuthConstants.TOKEN));
        return orderService.delete(userId, id);
    }

    @GetMapping("get/{id}")
    public InvokerResult get(@PathVariable("id") Long id) {
        return orderService.get(id);
    }

    @GetMapping("cancel")
    public InvokerResult cancel(@RequestParam("id") Long id) throws BsUserException {
        Long userId = TokenUtil.getUserId(request.getHeader(AuthConstants.TOKEN));
        return orderService.cancel(userId, id);
    }
}
