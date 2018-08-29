package com.qtxln.goods.service;

import com.qtxln.goods.mapper.GoodsClassMapper;
import com.qtxln.model.common.VueTree;
import com.qtxln.model.goods.GoodsClass;
import com.qtxln.transport.InvokerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author QT
 * 2018-07-23 18:27
 */
@Service
public class GoodsClassService {
    private final GoodsClassMapper goodsClassMapper;

    @Autowired
    public GoodsClassService(GoodsClassMapper goodsClassMapper) {
        this.goodsClassMapper = goodsClassMapper;
    }

    public InvokerResult selectAll() {
        List<GoodsClass> goodsClasses = goodsClassMapper.selectAll();
        List<VueTree> treeList = new ArrayList<>();
        goodsClasses.forEach(goodsClass -> {
            if (goodsClass.getParentId() == 0) {
                treeList.add(assembleBean(goodsClass));
            }
        });
        treeList.forEach(vueTree -> vueTree.setChildren(getChild(vueTree.getId(), goodsClasses)));
        return InvokerResult.getInstance(treeList);
    }

    @Transactional(rollbackFor = Exception.class)
    public InvokerResult insert(GoodsClass goodsClass) {
        goodsClassMapper.insert(goodsClass);
        int isParentTrue = 1;
        goodsClassMapper.updateParent(goodsClass.getParentId(), isParentTrue);
        return InvokerResult.getInstance();
    }

    @Transactional(rollbackFor = Exception.class)
    public InvokerResult delete(long id, long parentId) {
        goodsClassMapper.delete(id);
        int i = goodsClassMapper.selectByParentId(parentId);
        if (i == 0) {
            int isParentFalse = 0;
            goodsClassMapper.updateParent(parentId, isParentFalse);
        }
        return InvokerResult.getInstance();
    }

    /**
     * 获取子分类
     */
    private List<VueTree> getChild(Long id, List<GoodsClass> goodsClasses) {
        List<VueTree> childList = new ArrayList<>();
        goodsClasses.forEach(goodsClass -> {
            if (Objects.equals(goodsClass.getParentId(), id)) {
                childList.add(assembleBean(goodsClass));
            }
        });
        childList.forEach(vueTree -> {
            if (vueTree.isParent()) {
                vueTree.setChildren(getChild(vueTree.getId(), goodsClasses));
            }
        });
        return childList;
    }

    /**
     * 组装bean
     */
    private VueTree assembleBean(GoodsClass goodsClass) {
        VueTree vueTree = new VueTree();
        vueTree.setId(goodsClass.getId());
        vueTree.setTitle(goodsClass.getClassName());
        vueTree.setExpand(true);
        vueTree.setParent(goodsClass.getIsParent());
        vueTree.setParentId(goodsClass.getParentId());
        return vueTree;
    }
}
