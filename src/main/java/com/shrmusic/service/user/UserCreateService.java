package com.shrmusic.service.user;

import com.shrmusic.entity.user.User;
import com.shrmusic.repository.user.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreateService {
    @Autowired
    private UserJpaRepository userJpaRepository;

    public User create(User user){
        userJpaRepository.save(user);
        return user;
    }

    public User findById(Long id){
        return userJpaRepository.findOne(id);
    }
}
