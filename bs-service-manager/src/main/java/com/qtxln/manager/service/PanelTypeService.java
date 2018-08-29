package com.qtxln.manager.service;

import com.qtxln.manager.mapper.PanelTypeMapper;
import com.qtxln.model.manager.PanelType;
import com.qtxln.transport.InvokerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author QT
 * 2018-08-03 15:37
 */
@Service
public class PanelTypeService {
    private final PanelTypeMapper panelTypeMapper;

    @Autowired
    public PanelTypeService(PanelTypeMapper panelTypeMapper) {
        this.panelTypeMapper = panelTypeMapper;
    }

    public InvokerResult insert(PanelType panelType){
        panelTypeMapper.insert(panelType);
        return InvokerResult.getInstance();
    }

    public InvokerResult selectAll(){
        return InvokerResult.getInstance(panelTypeMapper.selectAll());
    }
}
