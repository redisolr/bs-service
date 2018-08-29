package com.qtxln.user.controller;

import com.qtxln.model.user.dto.RoleDTO;
import com.qtxln.transport.InvokerResult;
import com.qtxln.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author QT
 * 2018-08-15 16:05
 */
@RestController
@RequestMapping("role")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("list")
    public InvokerResult findAll(@RequestParam(value = "pageNum",defaultValue = "0") Integer pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "0") Integer pageSize) {
        return roleService.findAll(pageNum, pageSize);
    }

    @PostMapping("insert")
    public InvokerResult insert(@RequestBody RoleDTO roleDTO) {
        return roleService.insert(roleDTO);
    }

    @PutMapping("update")
    public InvokerResult update(@RequestBody RoleDTO roleDTO) {
        return roleService.update(roleDTO);
    }

    @DeleteMapping("delete")
    public InvokerResult delete(@RequestParam("id") Long id) {
        return roleService.delete(id);
    }

    @GetMapping("get/{id}")
    public InvokerResult findById(@PathVariable("id") Long id) {
        return roleService.findById(id);
    }
}
