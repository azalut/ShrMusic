package com.shrmusic.service;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxRequestConfig;
import com.shrmusic.entity.user.User;
import com.shrmusic.repository.user.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

public abstract class DbxAbstractService {
    @Autowired
    protected UserJpaRepository userJpaRepository;

    protected DbxClient createDbxClient(final Long id){
        User user = userJpaRepository.findOne(id);
        if(user == null){
            return null;
        }

        DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
        return new DbxClient(config, user.getAccessToken());
    }
}
