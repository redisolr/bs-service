package com.qtxln.search.controller;

import com.qtxln.exception.BsSearchException;
import com.qtxln.model.goods.Goods;
import com.qtxln.search.service.BookGoodsService;
import com.qtxln.transport.InvokerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author QT
 * 2018-07-31 16:27
 */
@RestController
@RequestMapping("es/goods")
public class BookGoodsController {
    private final BookGoodsService bookGoodsService;

    @Autowired
    public BookGoodsController(BookGoodsService bookGoodsService) {
        this.bookGoodsService = bookGoodsService;
    }

    @GetMapping("saveAll")
    public InvokerResult saveAll() {
        return bookGoodsService.saveAll();
    }

    @GetMapping("deleteAll")
    public InvokerResult deleteAll() {
        return bookGoodsService.deleteAll();
    }

    @GetMapping("search")
    public InvokerResult search(@RequestParam(value = "keyWord", defaultValue = "") String keyWord,
                                @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                @RequestParam(value = "sort", defaultValue = "gmtCreate") String sort,
                                @RequestParam(value = "direction", defaultValue = "1") String direction) {
        return bookGoodsService.search(keyWord, pageNum, pageSize, sort, direction);
    }

    @GetMapping("findById")
    public Goods saveAll(@RequestParam("id") long id) throws BsSearchException {
        return bookGoodsService.findById(id);
    }
}
