package com.shrmusic.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/user/account")
public class UserAccountController {
    @RequestMapping(value = {"", "/", "/home"})
    public String test(Principal principal){
        return "This is you account, " + principal.getName();
    }
}
