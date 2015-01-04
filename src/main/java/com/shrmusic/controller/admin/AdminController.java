package com.shrmusic.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @RequestMapping(value = {"", "/", "/home"})
    public String home(){
        return "hi admin, how are you?";
    }
}
