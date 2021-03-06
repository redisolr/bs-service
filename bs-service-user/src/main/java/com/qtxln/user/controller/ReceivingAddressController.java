package com.qtxln.user.controller;

import com.qtxln.constants.AuthConstants;
import com.qtxln.exception.BsUserException;
import com.qtxln.model.user.ReceivingAddress;
import com.qtxln.model.user.User;
import com.qtxln.transport.InvokerResult;
import com.qtxln.user.service.ReceivingAddressService;
import com.qtxln.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author QT
 * 2018-08-28 15:31
 */
@RestController
@RequestMapping("receivingAddress")
public class ReceivingAddressController {
    private final ReceivingAddressService receivingAddressService;
    private final HttpServletRequest request;

    @Autowired
    public ReceivingAddressController(ReceivingAddressService receivingAddressService, HttpServletRequest request) {
        this.receivingAddressService = receivingAddressService;
        this.request = request;
    }

    @PostMapping("insert")
    public InvokerResult insert(@RequestBody ReceivingAddress receivingAddress) throws BsUserException, IOException {
        User user = TokenUtil.getUserId(request.getHeader(AuthConstants.TOKEN));
        receivingAddress.setUserId(user.getId());
        return receivingAddressService.insert(receivingAddress);
    }

    @GetMapping("findAll")
    public InvokerResult findAll() throws BsUserException, IOException {
        User user = TokenUtil.getUserId(request.getHeader(AuthConstants.TOKEN));
        return receivingAddressService.findAll(user.getId());
    }

    @GetMapping("findById/{id}")
    public InvokerResult findById(@PathVariable("id") Long id) {
        return receivingAddressService.findById(id);
    }

    @PutMapping("update")
    public InvokerResult update(@RequestBody ReceivingAddress receivingAddress) throws BsUserException, IOException {
        User user = TokenUtil.getUserId(request.getHeader(AuthConstants.TOKEN));
        receivingAddress.setUserId(user.getId());
        return receivingAddressService.update(receivingAddress);
    }

    @DeleteMapping("delete")
    public InvokerResult delete(@RequestParam("id") Long id) {
        return receivingAddressService.delete(id);
    }
}
