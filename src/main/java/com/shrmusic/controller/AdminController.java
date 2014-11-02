package com.shrmusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @RequestMapping(value = {"", "/", "/home"})
    @ResponseBody
    public String home(){
        return "hi admin, how are you?";
    }
}
