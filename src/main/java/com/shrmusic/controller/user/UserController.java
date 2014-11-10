package com.shrmusic.controller.user;

import com.shrmusic.entity.user.User;
import com.shrmusic.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    //TODO: handle username and password length exception somehow, figure out how and implement it
    
    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response){
        boolean isAdded = userService.addDefaultUserIfNotExists(username, password, true);
        if(isAdded){
            response.setStatus(HttpServletResponse.SC_CREATED);
        }else{
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public User find(@PathVariable("id") Long id, HttpServletResponse response){
        User user = userService.findById(id);
        if(user == null){
            response.setStatus(HttpStatus.NO_CONTENT.value());
        }else{
            response.setStatus(HttpStatus.OK.value());
        }
        return user;
    }
}
