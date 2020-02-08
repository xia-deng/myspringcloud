package com.lindeng.configserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ConfigController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/users")
    public String fetchUser(){
        StringBuffer strings = new StringBuffer("\n");
        for (int i = 0; i < 100; i++) {
            strings.append(restTemplate.getForEntity("http://client1/users/1", String.class).getBody()).append("\n");
        }
       return strings.toString();
    }
}
