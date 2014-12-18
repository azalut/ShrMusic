package com.shrmusic.service;

import com.shrmusic.entity.user.User;
import com.shrmusic.service.user.UserService;
import com.shrmusic.util.user.UserDetailsAssemblage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Base64;

@Service
public class BasicTokenService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsAssemblage userDetailsAssemblage;

    private String decodeToken(String token){
        return new String(Base64.getDecoder().decode(token));
    }

    public boolean validate(String token){
        String decodedToken = decodeToken(token);
        if(decodedToken.contains(".")){
            String[] usernamePasswordArray = decodedToken.split("\\.");
            User user = userService.findByUsername(usernamePasswordArray[0]);
            if(user != null){
                String userPasswordMD5 = user.getPassword();
                return userPasswordMD5.equals(usernamePasswordArray[1]);
            }
        }
        return false;
    }

    public UserDetails getUserFromToken(String token) {
        String decodedToken = decodeToken(token);
        if(decodedToken.contains(".")){
            String username = decodedToken.split("\\.")[0];
            User user = userService.findByUsername(username);
            if(user != null){
                return userDetailsAssemblage.assembleUser(user);
            }
        }
        return null;
    }
}
