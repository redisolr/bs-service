package com.qtxln.user.controller;

import com.qtxln.exception.BsUserException;
import com.qtxln.model.user.Admin;
import com.qtxln.transport.InvokerResult;
import com.qtxln.user.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author QT
 * 2018-08-14 14:39
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("insert")
    public InvokerResult insert(@RequestBody Admin admin) {
        return adminService.insert(admin);
    }

    @PostMapping("login")
    public InvokerResult login(@RequestBody Admin admin) throws BsUserException {
        return adminService.login(admin);
    }

    @GetMapping("checkTokenIsExpire/{token}")
    public boolean checkTokenIsExpire(@PathVariable("token") String token) {
        return adminService.checkTokenIsExpire(token);
    }

    @PostMapping("checkPermissions")
    public boolean checkPermissions(@RequestBody Map<String, String> map) {
        return adminService.checkPermissions(map);
    }

    @GetMapping("list")
    public InvokerResult selectAll(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return adminService.selectAll(pageNum, pageSize);
    }

    @PutMapping("update/enable")
    public InvokerResult updateEnableState(@RequestBody Admin admin) {
        return adminService.updateEnableState(admin);
    }

    @GetMapping("get/{id}")
    public InvokerResult get(@PathVariable("id") Long id){
        return adminService.get(id);
    }

    @PutMapping("update")
    public InvokerResult update(@RequestBody Admin admin) {
        return adminService.update(admin);
    }

    @DeleteMapping("delete")
    public InvokerResult delete(@RequestParam("id") Long id){
        return adminService.delete(id);
    }
}
