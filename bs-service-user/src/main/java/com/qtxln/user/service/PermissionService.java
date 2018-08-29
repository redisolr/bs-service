package com.qtxln.user.service;

import com.qtxln.model.common.VueTree;
import com.qtxln.model.user.Permission;
import com.qtxln.transport.InvokerResult;
import com.qtxln.user.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author QT
 * 2018-08-17 16:30
 */
@Service
public class PermissionService {
    private final PermissionMapper permissionMapper;

    @Autowired
    public PermissionService(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    public InvokerResult selectAll() {
        List<Permission> permissions = permissionMapper.selectAll();
        List<VueTree> treeList = new ArrayList<>();
        permissions.forEach(permission -> {
            if (permission.getParentId() == 0) {
                treeList.add(assembleBean(permission));
            }
        });
        treeList.forEach(vueTree -> vueTree.setChildren(getChild(vueTree.getId(), permissions)));
        return InvokerResult.getInstance(treeList);
    }


    @Transactional(rollbackFor = Exception.class)
    public InvokerResult insert(Permission permission) {
        permissionMapper.insert(permission);
        int isParentTrue = 1;
        permissionMapper.updateParent(permission.getParentId(), isParentTrue);
        return InvokerResult.getInstance();
    }

    public InvokerResult get(Long id) {
        return InvokerResult.getInstance(permissionMapper.selectById(id));
    }

    public InvokerResult update(Permission permission) {
        permissionMapper.update(permission);
        return InvokerResult.getInstance();
    }

    @Transactional(rollbackFor = Exception.class)
    public InvokerResult delete(Long id, Long parentId) {
        permissionMapper.delete(id);
        int i = permissionMapper.selectByParentId(parentId);
        if (i == 0) {
            int isParentFalse = 0;
            permissionMapper.updateParent(parentId, isParentFalse);
        }
        return InvokerResult.getInstance();
    }

    /**
     * 获取子分类
     */
    private List<VueTree> getChild(Long id, List<Permission> permissions) {
        List<VueTree> childList = new ArrayList<>();
        permissions.forEach(permission -> {
            if (Objects.equals(permission.getParentId(), id)) {
                childList.add(assembleBean(permission));
            }
        });
        childList.forEach(vueTree -> {
            if (vueTree.isParent()) {
                vueTree.setChildren(getChild(vueTree.getId(), permissions));
            }
        });
        return childList;
    }

    /**
     * 组装bean
     */
    private VueTree assembleBean(Permission permission) {
        VueTree vueTree = new VueTree();
        vueTree.setId(permission.getId());
        vueTree.setTitle(permission.getName());
        vueTree.setExpand(false);
        vueTree.setParent(permission.getIsParent());
        vueTree.setParentId(permission.getParentId());
        return vueTree;
    }
}
