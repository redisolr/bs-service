package com.qtxln.goods.controller;

import com.qtxln.goods.service.GoodsClassService;
import com.qtxln.model.goods.GoodsClass;
import com.qtxln.transport.InvokerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author QT
 * 2018-07-25 11:40
 */
@RestController
@RequestMapping("goodsClass")
public class GoodsClassController {
    private final GoodsClassService goodsClassService;

    @Autowired
    public GoodsClassController(GoodsClassService goodsClassService) {
        this.goodsClassService = goodsClassService;
    }

    @GetMapping("all")
    public InvokerResult selectAll() {
        return goodsClassService.selectAll();
    }

    @PostMapping("insert")
    public InvokerResult insert(@RequestBody GoodsClass goodsClass) {
        return goodsClassService.insert(goodsClass);
    }

    @DeleteMapping("delete")
    public InvokerResult delete(@RequestParam("id") long id, @RequestParam("parentId") long parentId) {
        return goodsClassService.delete(id, parentId);
    }
}
