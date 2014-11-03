package com.shrmusic.controller;

import com.shrmusic.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    //TODO: implement user controller
}
