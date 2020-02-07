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
        List<String> strings = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            strings.add(restTemplate.getForEntity("http://client1/users/1", String.class).getBody());
        }
       return "".join(",",strings);
    }
}
