package com.shrmusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InitController {
    //TODO: remember about @Transactional annotation while @PersistenceContext in repositories
    @RequestMapping(value = {"", "/", "/home"})
    @ResponseBody
    public String test(){
        return "This works fine.";
    }
}
