package com.qtxln.manager.controller;

import com.qtxln.manager.service.HomeService;
import com.qtxln.transport.InvokerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author QT
 * 2018-08-22 16:42
 */
@RestController
@RequestMapping("home")
public class HomeController {
    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("home")
    public InvokerResult home(){
        return homeService.home();
    }
}
