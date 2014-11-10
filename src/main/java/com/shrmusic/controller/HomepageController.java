package com.shrmusic.controller;

import com.shrmusic.entity.user.Role;
import com.shrmusic.entity.user.User;
import com.shrmusic.repository.user.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomepageController {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @RequestMapping(value = {"", "/", "/home"})
    @ResponseBody
    public String test(){
        Set<Role> roles = new HashSet<>(1);
        roles.add(new Role("ROLE_ADMIN"));
        User user = new User("admin", "admin", true, roles);
        userJpaRepository.save(user);
        return "This works fine.";
    }
}
