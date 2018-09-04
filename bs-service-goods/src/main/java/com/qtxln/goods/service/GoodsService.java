package com.qtxln.goods.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtxln.goods.mapper.GoodsClassMapper;
import com.qtxln.goods.mapper.GoodsDescMapper;
import com.qtxln.goods.mapper.GoodsMapper;
import com.qtxln.goods.message.GoodsMessageProvider;
import com.qtxln.model.goods.Goods;
import com.qtxln.model.goods.GoodsClass;
import com.qtxln.model.goods.GoodsDesc;
import com.qtxln.model.goods.dto.GoodsDTO;
import com.qtxln.transport.InvokerResult;
import com.qtxln.util.PageDataUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author QT
 * 2018-07-23 18:28
 */
@Service
public class GoodsService {
    private final GoodsMapper goodsMapper;
    private final GoodsDescMapper goodsDescMapper;
    private final GoodsClassMapper goodsClassMapper;
    private final GoodsMessageProvider goodsMessageProvider;

    @Autowired
    public GoodsService(GoodsMapper goodsMapper, GoodsDescMapper goodsDescMapper,
                        GoodsClassMapper goodsClassMapper, GoodsMessageProvider goodsMessageProvider) {
        this.goodsMapper = goodsMapper;
        this.goodsDescMapper = goodsDescMapper;
        this.goodsClassMapper = goodsClassMapper;
        this.goodsMessageProvider = goodsMessageProvider;
    }

    public InvokerResult listGoods(GoodsDTO goodsDTO) {
        PageHelper.startPage(goodsDTO.getPageNum(), goodsDTO.getPageSize());
        List<Goods> goodsList = goodsMapper.selectAll();
        return InvokerResult.getInstance(PageDataUtil.toPageData(new PageInfo<>(goodsList)));
    }

    @Transactional
    public InvokerResult insert(GoodsDTO goodsDTO) {
        Goods goods = new Goods();
        goods.setGoodsName(goodsDTO.getGoodsName());
        goods.setGoodsBarcode(goodsDTO.getGoodsBarcode());
        goods.setGoodsNum(goodsDTO.getGoodsNum());
        goods.setGoodsCid(goodsDTO.getGoodsCid());
        goods.setGoodsPrice(goodsDTO.getGoodsPrice());
        goods.setGoodsStatus(goodsDTO.getGoodsStatus());
        goods.setGoodsImage(goodsDTO.getGoodsImage());
        goodsMapper.insertSelective(goods);
        GoodsDesc goodsDesc = new GoodsDesc();
        goodsDesc.setGoodsId(goods.getId());
        goodsDesc.setGoodsDesc(goodsDTO.getGoodsDesc());
        goodsDescMapper.insert(goodsDesc);
        // 发布上架商品,通过RabbitMq通知更新ES
        if (goods.getGoodsStatus() == 1){
            goodsMessageProvider.insertGoodsMessage(goods.getId());
        }
        return InvokerResult.getInstance();
    }

    public InvokerResult getGoods(long id) {
        Goods goods = goodsMapper.selectById(id);
        GoodsDesc goodsDesc = goodsDescMapper.selectByGoodsId(id);
        GoodsClass goodsClass = goodsClassMapper.selectById(goods.getGoodsCid());
        GoodsDTO goodsDTO = new GoodsDTO();
        BeanUtils.copyProperties(goods, goodsDTO);
        BeanUtils.copyProperties(goodsDesc, goodsDTO);
        BeanUtils.copyProperties(goodsClass, goodsDTO);
        return InvokerResult.getInstance(goodsDTO);
    }

    public Goods getById(long id) {
        Goods goods = goodsMapper.selectById(id);
        String[] split = goods.getGoodsImage().split(",");
        goods.setGoodsImage(split[0]);
        return goods;
    }

    public InvokerResult update(GoodsDTO goodsDTO) {
        Goods goods = new Goods();
        goods.setId(goodsDTO.getId());
        goods.setGoodsName(goodsDTO.getGoodsName());
        goods.setGoodsBarcode(goodsDTO.getGoodsBarcode());
        goods.setGoodsNum(goodsDTO.getGoodsNum());
        goods.setGoodsCid(goodsDTO.getGoodsCid());
        goods.setGoodsPrice(goodsDTO.getGoodsPrice());
        goods.setGoodsStatus(goodsDTO.getGoodsStatus());
        goods.setGoodsImage(goodsDTO.getGoodsImage());
        goodsMapper.updateById(goods);
        GoodsDesc goodsDesc = new GoodsDesc();
        goodsDesc.setGoodsId(goodsDTO.getId());
        goodsDesc.setGoodsDesc(goodsDTO.getGoodsDesc());
        goodsDescMapper.updateByGoodsId(goodsDesc);
        // 更新上架商品,通过RabbitMq通知更新ES
        if (goods.getGoodsStatus() == 1){
            goodsMessageProvider.updateGoodsMessage(goods.getId());
        }
        return InvokerResult.getInstance();
    }

    public InvokerResult deleteById(long id) {
        goodsMapper.deleteById(id);
        // 删除商品,通过RabbitMq通知更新ES
        goodsMessageProvider.deleteGoodsMessage(id);
        return InvokerResult.getInstance();
    }

    public InvokerResult deleteByIds(GoodsDTO goodsDTO) {
        goodsMapper.deleteByIds(goodsDTO.getIds());
        // 删除商品,通过RabbitMq通知更新ES
        goodsDTO.getIds().forEach(goodsMessageProvider::deleteGoodsMessage);
        return InvokerResult.getInstance();
    }
}
