package com.shrmusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/init")
public class InitController {
    //TODO: remember about @Transactional annotation while @PersistenceContext in repositories
    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(){
        return "This works fine.";
    }
}
