package com.lindeng.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author linde
 * @Date 2020/7/22 14:15
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String getStr(){
        return "this is oauth2.0 test page.<a href='/logout'> Logout</a>";
    }
}
