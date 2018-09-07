package com.qtxln.manager.service;

import com.qtxln.manager.client.IGoodsClient;
import com.qtxln.manager.mapper.PanelContentMapper;
import com.qtxln.manager.mapper.PanelMapper;
import com.qtxln.model.goods.Goods;
import com.qtxln.model.manager.dto.PanelContentDTO;
import com.qtxln.model.manager.dto.PanelDTO;
import com.qtxln.transport.InvokerResult;
import com.qtxln.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author QT
 * 2018-08-22 16:44
 */
@Service
@Slf4j
public class HomeService {
    private final PanelMapper panelMapper;
    private final PanelContentMapper panelContentMapper;
    private final IGoodsClient goodsClient;

    @Autowired
    public HomeService(PanelMapper panelMapper, PanelContentMapper panelContentMapper, IGoodsClient goodsClient) {
        this.panelMapper = panelMapper;
        this.panelContentMapper = panelContentMapper;
        this.goodsClient = goodsClient;
    }

    public InvokerResult home() {
        List<PanelDTO> panelDTOS = panelMapper.selectAll();
        panelDTOS.forEach(panelDTO -> {
            List<PanelContentDTO> panelContents = panelContentMapper.selectByPanelId(panelDTO.getId());
            if (panelDTO.getTypeId() != 1 && panelContents.size() > 0) {
                panelContents.forEach(panelContentDTO -> {
                    try {
                        Goods goods = JsonUtil.jsonToObj(goodsClient.getGoods(panelContentDTO.getGoodsId()), Goods.class);
                        BeanUtils.copyProperties(goods, panelContentDTO);
                    } catch (IOException e) {
                        log.error("商品序列化异常");
                    }
                });
            }
            panelDTO.setPanelContents(panelContents);
        });
        return InvokerResult.getInstance(panelDTOS);
    }
}
