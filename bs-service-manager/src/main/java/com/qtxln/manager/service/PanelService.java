package com.qtxln.manager.service;

import com.qtxln.manager.mapper.PanelMapper;
import com.qtxln.model.manager.Panel;
import com.qtxln.transport.InvokerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author QT
 * 2018-08-03 17:32
 */
@Service
public class PanelService {
    private final PanelMapper panelMapper;

    @Autowired
    public PanelService(PanelMapper panelMapper) {
        this.panelMapper = panelMapper;
    }

    public InvokerResult insert(Panel panel){
        panelMapper.insert(panel);
        return InvokerResult.getInstance();
    }

    public InvokerResult selectAll(){
        return InvokerResult.getInstance(panelMapper.selectAll());
    }
}
