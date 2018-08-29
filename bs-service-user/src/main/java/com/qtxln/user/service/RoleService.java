package com.qtxln.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtxln.model.user.Role;
import com.qtxln.model.user.RolePermission;
import com.qtxln.model.user.dto.RoleDTO;
import com.qtxln.transport.InvokerResult;
import com.qtxln.user.mapper.RoleMapper;
import com.qtxln.user.mapper.RolePermissionMapper;
import com.qtxln.user.task.PermissionAsyncTask;
import com.qtxln.util.PageDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author QT
 * 2018-08-15 15:36
 */
@Service
public class RoleService {
    private final RoleMapper roleMapper;
    private final RolePermissionMapper rolePermissionMapper;
    private final PermissionAsyncTask permissionAsyncTask;

    @Autowired
    public RoleService(RoleMapper roleMapper, RolePermissionMapper rolePermissionMapper,
                       PermissionAsyncTask permissionAsyncTask) {
        this.roleMapper = roleMapper;
        this.rolePermissionMapper = rolePermissionMapper;
        this.permissionAsyncTask = permissionAsyncTask;
    }

    public InvokerResult findAll(Integer pageNum, Integer pageSize){
        if (pageNum != 0 && pageSize != 0){
            PageHelper.startPage(pageNum, pageSize);
            List<Role> roleList = roleMapper.findAll();
            return InvokerResult.getInstance(PageDataUtil.toPageData(new PageInfo<>(roleList)));
        }
        return InvokerResult.getInstance(roleMapper.findAll());
    }

    @Transactional(rollbackFor = Exception.class)
    public InvokerResult insert(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        roleMapper.insert(role);
        roleDTO.getRolePermissionList().forEach(rolePermission -> rolePermission.setRoleId(role.getId()));
        rolePermissionMapper.insertBatch(roleDTO.getRolePermissionList());
        // 将数据保存到redis中
        permissionAsyncTask.insertPermissionTask(role.getId());
        return InvokerResult.getInstance();
    }

    public InvokerResult findById(Long id){
        Role role = roleMapper.findById(id);
        List<RolePermission> rolePermissionList = rolePermissionMapper.findByRoleId(id);
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(role.getName());
        roleDTO.setRolePermissionList(rolePermissionList);
        return InvokerResult.getInstance(roleDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    public InvokerResult update(RoleDTO roleDTO) {
        delete(roleDTO.getId());
        insert(roleDTO);
        return InvokerResult.getInstance();
    }

    @Transactional(rollbackFor = Exception.class)
    public InvokerResult delete(Long id) {
        roleMapper.deleteById(id);
        rolePermissionMapper.deleteByRoleId(id);
        // 将数从redis中删除
        permissionAsyncTask.deletePermissionTask(id);
        return InvokerResult.getInstance();
    }
}
