package com.shrmusic.service.user;

import com.dropbox.core.*;
import com.shrmusic.entity.user.User;
import com.shrmusic.repository.user.UserJpaRepository;
import com.shrmusic.service.CurrentAuthenticatedUserService;
import com.shrmusic.util.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class DbxAuthService {
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CurrentAuthenticatedUserService currentAuthenticatedUserService;
    private DbxWebAuthNoRedirect webAuthNoRedirect;
    private final RoleEnum roleTOKENSAVED = RoleEnum.ROLE_TOKENSAVED;

    public boolean userExists(final Long id){
        User user = userJpaRepository.findOne(id);
        return user != null;
    }

    public String returnAuthUrl(final String appkey, final String appsecret){
        DbxAppInfo appInfo = new DbxAppInfo(appkey, appsecret);
        DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());

        webAuthNoRedirect = new DbxWebAuthNoRedirect(config, appInfo);
        return webAuthNoRedirect.start();
    }

    @Transactional
    public boolean createTokenAndSaveToDB(final String authKey){
        User user = currentAuthenticatedUserService.getCurrentAuthenticatedUser();
        try {
            DbxAuthFinish authFinish = webAuthNoRedirect.finish(authKey);
            user.setAccessToken(authFinish.accessToken);
            userService.addRole(roleTOKENSAVED.getRole());
            return true;
        } catch (DbxException e) {
            return false;
        }
    }
}
