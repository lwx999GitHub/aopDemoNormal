package com.example.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name){
        //System.out.println("hello "+name);
        int s=1/0;
        return "hello "+name;
    }

}
