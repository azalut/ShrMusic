package com.shrmusic.controller.user;

import com.shrmusic.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}/token", method = RequestMethod.PUT)
    public void setToken(@PathVariable("id") long id, @RequestParam("token") final String token, HttpServletResponse response){
        boolean isAdded = userService.setAccessToken(id, token);
        if(isAdded){
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }


}
