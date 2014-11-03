package com.shrmusic.controller;

import com.shrmusic.entity.user.Role;
import com.shrmusic.entity.user.User;
import com.shrmusic.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    //TODO: implement user controller

    @RequestMapping(value = "/add")
    @ResponseBody
    public User addSampleAdminUser(){
        Set<Role> set = new HashSet<>();
        set.add(new Role("ROLE_ADMIN"));
        User user = new User("admin", "admin", true, set);
        userRepository.create(user);
        return user;
    }
}
