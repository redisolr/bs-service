package com.qtxln.manager.controller;

import com.qtxln.manager.service.PanelContentService;
import com.qtxln.model.manager.PanelContent;
import com.qtxln.transport.InvokerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author QT
 * 2018-08-06 17:51
 */
@RestController
@RequestMapping("panelContent")
public class PanelContentController {
    private final PanelContentService panelContentService;

    @Autowired
    public PanelContentController(PanelContentService panelContentService) {
        this.panelContentService = panelContentService;
    }

    @GetMapping("get/{panelId}")
    public InvokerResult selectByPanelId(@PathVariable("panelId") Long panelId) {
        return panelContentService.selectByPanelId(panelId);
    }

    @PostMapping("insert")
    public InvokerResult insert(@RequestBody PanelContent panelContent) {
        return panelContentService.insert(panelContent);
    }

    @GetMapping("getPanelContent/{id}")
    public InvokerResult getPanelContent(@PathVariable("id") Long id) {
        return panelContentService.getPanelContent(id);
    }

    @PutMapping("update")
    public InvokerResult update(@RequestBody PanelContent panelContent) {
        return panelContentService.update(panelContent);
    }

    @DeleteMapping("delete")
    public InvokerResult delete(@RequestParam("id") Long id) {
        return panelContentService.delete(id);
    }
}
