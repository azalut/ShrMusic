package com.shrmusic.service.user;

import com.shrmusic.repository.user.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreateService {
    @Autowired
    private UserJpaRepository userJpaRepository;

    //TODO: implement service which adds user if not exists; create relation repository->service->controller
}
