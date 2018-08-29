package com.qtxln.manager.controller;

import com.qtxln.manager.service.PanelTypeService;
import com.qtxln.model.manager.PanelType;
import com.qtxln.transport.InvokerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author QT
 * 2018-08-03 15:39
 */
@RestController
@RequestMapping("panelType")
public class PanelTypeController {
    private final PanelTypeService panelTypeService;

    @Autowired
    public PanelTypeController(PanelTypeService panelTypeService) {
        this.panelTypeService = panelTypeService;
    }

    @PostMapping("insert")
    public InvokerResult insert(@RequestBody PanelType panelType) {
        panelTypeService.insert(panelType);
        return InvokerResult.getInstance();
    }

    @GetMapping("list")
    public InvokerResult selectAll() {
        return panelTypeService.selectAll();
    }
}
