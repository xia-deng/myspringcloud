package com.lindeng.cloudclient1.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Value("${locale.data.name}")
    private String name;
    @Value("${locale.data.age}")
    private int age;
    @Value("${locale.data.gender}")
    private String gender;

    @GetMapping("/users/{id}")
    public JsonNode getUser(@PathVariable String id){
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put("name", name);
        objectNode.put("age", age);
        objectNode.put("gender", gender);
        return objectNode;
    }
}
