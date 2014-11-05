package com.shrmusic.controller.user;

import com.shrmusic.entity.user.Role;
import com.shrmusic.entity.user.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/user")
public class UserCreateController {
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public User giveExample(){
        Set<Role> set = new HashSet<>();
        set.add(new Role("ROLE_USER"));
        return new User("exampleUN", "examplePass", true, set);
    }
}
