package com.qtxln.goods.controller;

import com.qtxln.exception.BsGoodsException;
import com.qtxln.goods.service.GoodsService;
import com.qtxln.model.goods.Goods;
import com.qtxln.model.goods.dto.GoodsDTO;
import com.qtxln.transport.InvokerResult;
import com.qtxln.util.AliOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author QT
 * 2018-07-24 10:57
 */
@RestController
@RequestMapping("goods")
public class GoodsController {
    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @PostMapping("list")
    public InvokerResult listGoods(@RequestBody GoodsDTO goodsDTO) {
        return goodsService.listGoods(goodsDTO);
    }

    @PostMapping("insert")
    public InvokerResult insertGoods(@RequestBody GoodsDTO goodsDTO) {
        return goodsService.insert(goodsDTO);
    }

    @GetMapping("get")
    public InvokerResult getGoods(@RequestParam("id") long id) {
        return goodsService.getGoods(id);
    }

    @PutMapping("update")
    public InvokerResult updateGoods(@RequestBody GoodsDTO goodsDTO) {
        return goodsService.update(goodsDTO);
    }

    @DeleteMapping("deleteById")
    public InvokerResult deleteById(@RequestParam("id") long id) {
        return goodsService.deleteById(id);
    }

    @DeleteMapping("deleteByIds")
    public InvokerResult deleteByIds(@RequestBody GoodsDTO goodsDTO) {
        return goodsService.deleteByIds(goodsDTO);
    }

    @PostMapping("upload")
    public InvokerResult upload(MultipartFile file) {
        return InvokerResult.getInstance(AliOSSUtil.upload(file));
    }

    @GetMapping("getById")
    public Goods getById(@RequestParam("id") long id) {
        return goodsService.getById(id);
    }
}
