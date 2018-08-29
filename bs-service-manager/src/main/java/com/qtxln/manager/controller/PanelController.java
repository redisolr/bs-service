package com.qtxln.manager.controller;

import com.qtxln.manager.service.PanelService;
import com.qtxln.model.manager.Panel;
import com.qtxln.transport.InvokerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author QT
 * 2018-08-03 17:32
 */
@RestController
@RequestMapping("panel")
public class PanelController {
    private final PanelService panelService;

    @Autowired
    public PanelController(PanelService panelService) {
        this.panelService = panelService;
    }

    @PostMapping("insert")
    public InvokerResult insert(@RequestBody Panel panel){
        return panelService.insert(panel);
    }

    @GetMapping("list")
    public InvokerResult selectAll(){
        return panelService.selectAll();
    }
}
