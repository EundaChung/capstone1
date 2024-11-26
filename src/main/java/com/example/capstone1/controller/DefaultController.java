package com.example.capstone1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class DefaultController {
    @GetMapping("/index")
    public String index(){
        return "test test test";
    }

}
