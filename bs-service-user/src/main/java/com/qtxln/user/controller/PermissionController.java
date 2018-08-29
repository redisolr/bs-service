package com.qtxln.user.controller;

import com.qtxln.model.user.Permission;
import com.qtxln.transport.InvokerResult;
import com.qtxln.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author QT
 * 2018-08-17 16:37
 */
@RestController
@RequestMapping("permission")
public class PermissionController {
    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("selectAll")
    public InvokerResult selectAll() {
        return permissionService.selectAll();
    }

    @PostMapping("insert")
    public InvokerResult insert(@RequestBody Permission permission) {
        return permissionService.insert(permission);
    }

    @GetMapping("get/{id}")
    public InvokerResult get(@PathVariable("id") Long id) {
        return permissionService.get(id);
    }

    @PutMapping("update")
    public InvokerResult update(@RequestBody Permission permission) {
        return permissionService.update(permission);
    }

    @DeleteMapping("delete")
    public InvokerResult delete(@RequestParam("id") Long id, @RequestParam("parentId") Long parentId) {
        return permissionService.delete(id, parentId);
    }
}
