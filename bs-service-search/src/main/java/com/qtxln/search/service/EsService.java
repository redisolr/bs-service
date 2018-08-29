package com.qtxln.search.service;

import com.qtxln.transport.InvokerResult;
import org.springframework.stereotype.Service;

/**
 * @author QT
 * 2018-07-31 18:43
 */
@Service
public class EsService {

    public InvokerResult getEsInfo(){
        return InvokerResult.getInstance();
    }
}
