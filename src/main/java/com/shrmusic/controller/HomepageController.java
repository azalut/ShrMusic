package com.shrmusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomepageController {
    @RequestMapping(value = {"", "/", "/home"})
    @ResponseBody
    public String test(){
        return "This works fine.";
    }
}
