package com.shrmusic.service;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxRequestConfig;
import com.shrmusic.entity.user.User;
import com.shrmusic.repository.user.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CurrentAuthUserService {
    @Autowired
    private UserJpaRepository userJpaRepository;

    private User getCurrentAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userJpaRepository.findByUsername(authentication.getName());
    }

    public DbxClient getClient(){
        DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
        return new DbxClient(config, getCurrentAuthenticatedUser().getAccessToken());
    }
}
