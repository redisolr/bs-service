package com.qtxln.manager.service;

import com.qtxln.manager.mapper.PanelContentMapper;
import com.qtxln.model.manager.PanelContent;
import com.qtxln.transport.InvokerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author QT
 * 2018-08-06 17:51
 */
@Service
public class PanelContentService {
    private final PanelContentMapper panelContentMapper;

    @Autowired
    public PanelContentService(PanelContentMapper panelContentMapper) {
        this.panelContentMapper = panelContentMapper;
    }

    public InvokerResult selectByPanelId(Long panelId) {
        return InvokerResult.getInstance(panelContentMapper.selectByPanelId(panelId));
    }

    public InvokerResult insert(PanelContent panelContent){
        panelContentMapper.insert(panelContent);
        return InvokerResult.getInstance();
    }

    public InvokerResult getPanelContent(Long id){
        return InvokerResult.getInstance(panelContentMapper.getPanelContent(id));
    }

    public InvokerResult update(PanelContent panelContent){
        panelContentMapper.update(panelContent);
        return InvokerResult.getInstance();
    }

    public InvokerResult delete(Long id){
        panelContentMapper.delete(id);
        return InvokerResult.getInstance();
    }
}
