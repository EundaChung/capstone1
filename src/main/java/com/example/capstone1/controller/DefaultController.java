package com.example.capstone1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("")
@Controller
public class DefaultController {
    @GetMapping({"/index"})
    public String index() {
        return "index";
    }
    @GetMapping({"/test"})
    public String test() {
        return "test";
    }
}