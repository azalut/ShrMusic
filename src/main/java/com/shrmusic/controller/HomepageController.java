package com.shrmusic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomepageController {
    @RequestMapping(value = {"", "/", "/home"})
    public String test(){
        return "This works fine.";
    }
}