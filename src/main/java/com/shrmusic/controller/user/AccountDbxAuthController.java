package com.shrmusic.controller.user;

import com.shrmusic.service.user.DbxAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequestMapping(value = "/account")
public class AccountDbxAuthController {
    @Autowired
    private DbxAuthService dbxAuthService;

    @RequestMapping(value = "/dbxauth", method = RequestMethod.GET)
    public String returnDbxAuthUrl(@RequestParam("appkey") final String appkey, @RequestParam("appsecret") final String appsecret){
        return dbxAuthService.returnAuthUrl(appkey, appsecret);
    }

    @RequestMapping(value = "/{authKey}", method = RequestMethod.PUT)
    public void createTokenWithAuthKey(@PathVariable("authKey") final String authKey, HttpServletResponse response){
        if(dbxAuthService.createTokenAndSaveToDB(authKey)){
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/whoami")
    public String whoami(Principal principal){
        return principal.getName();
    }
}
