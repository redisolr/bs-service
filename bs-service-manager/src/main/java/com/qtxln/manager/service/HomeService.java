package com.qtxln.manager.service;

import com.qtxln.manager.mapper.PanelContentMapper;
import com.qtxln.manager.mapper.PanelMapper;
import com.qtxln.model.manager.PanelContent;
import com.qtxln.model.manager.dto.PanelDTO;
import com.qtxln.transport.InvokerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author QT
 * 2018-08-22 16:44
 */
@Service
public class HomeService {
    private final PanelMapper panelMapper;
    private final PanelContentMapper panelContentMapper;

    @Autowired
    public HomeService(PanelMapper panelMapper, PanelContentMapper panelContentMapper) {
        this.panelMapper = panelMapper;
        this.panelContentMapper = panelContentMapper;
    }

    public InvokerResult home(){
        List<PanelDTO> panelDTOS = panelMapper.selectAll();
        panelDTOS.forEach(panelDTO -> {
            List<PanelContent> panelContents = panelContentMapper.selectByPanelId(panelDTO.getId());
            panelDTO.setPanelContents(panelContents);
        });
        return InvokerResult.getInstance(panelDTOS);
    }
}
