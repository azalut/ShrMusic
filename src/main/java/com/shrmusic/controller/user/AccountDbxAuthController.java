package com.shrmusic.controller.user;

import com.shrmusic.service.user.DbxAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/account/{id}")
public class AccountDbxAuthController {
    @Autowired
    private DbxAuthService dbxAuthService;

    @RequestMapping(value = "/dbxauth", method = RequestMethod.GET)
    public String returnDbxAuthUrl(@PathVariable("id") Long id, @RequestParam("appkey") final String appkey,
                                   @RequestParam("appsecret") final String appsecret, HttpServletResponse response){
        if(dbxAuthService.userExists(id)){
            response.setStatus(HttpServletResponse.SC_OK);
            return dbxAuthService.returnAuthUrl(appkey, appsecret);
        }else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

    }

    @RequestMapping(value = "/{authKey}", method = RequestMethod.PUT)
    public void createTokenWithAuthKey(@PathVariable("id") final Long id,
                                       @PathVariable("authKey") final String authKey, HttpServletResponse response){
        if(dbxAuthService.userExists(id) && dbxAuthService.createTokenAndSaveToDB(id, authKey)){
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
