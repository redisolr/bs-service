package com.qtxln.user.service;

import com.qtxln.model.user.ReceivingAddress;
import com.qtxln.transport.InvokerResult;
import com.qtxln.user.mapper.ReceivingAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author QT
 * 2018-08-28 15:30
 */
@Service
public class ReceivingAddressService {
    private final ReceivingAddressMapper receivingAddressMapper;

    @Autowired
    public ReceivingAddressService(ReceivingAddressMapper receivingAddressMapper) {
        this.receivingAddressMapper = receivingAddressMapper;
    }

    public InvokerResult insert(ReceivingAddress receivingAddress) {
        if (receivingAddress.getIsDefault()){
            receivingAddressMapper.updateNoDefault(receivingAddress.getUserId());
        }
        receivingAddressMapper.insert(receivingAddress);
        return InvokerResult.getInstance();
    }

    public InvokerResult findAll(Long userId) {
        return InvokerResult.getInstance(receivingAddressMapper.findAll(userId));
    }

    public InvokerResult findById(Long id) {
        return InvokerResult.getInstance(receivingAddressMapper.findById(id));
    }

    public InvokerResult update(ReceivingAddress receivingAddress) {
        if (receivingAddress.getIsDefault()){
            receivingAddressMapper.updateNoDefault(receivingAddress.getUserId());
        }
        receivingAddressMapper.update(receivingAddress);
        return InvokerResult.getInstance();
    }

    public InvokerResult delete(Long id) {
        receivingAddressMapper.delete(id);
        return InvokerResult.getInstance();
    }
}
