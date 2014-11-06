package com.shrmusic.controller.user;

import com.shrmusic.entity.user.Role;
import com.shrmusic.entity.user.User;
import com.shrmusic.service.user.UserCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/user")
public class UserCreateController {
    @Autowired
    private UserCreateService userCreateService;
    private final Role USER_ROLE = new Role("ROLE_USER");

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestParam("username") String username, @RequestParam("password") String password){
        Set<Role> roles = new HashSet<>(Arrays.asList(USER_ROLE));
        userCreateService.create(new User(username, password, true, roles));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public User find(@PathVariable("id") Long id, HttpServletResponse response){
        User user = userCreateService.findById(id);
        if(user == null){
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }else{
            response.setStatus(HttpStatus.OK.value());
        }
        return user;
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public void usernameAlreadyExists(){}

}
