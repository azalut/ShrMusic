package com.shrmusic.service.user;

import com.shrmusic.entity.user.Role;
import com.shrmusic.entity.user.User;
import com.shrmusic.repository.user.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserJpaRepository userJpaRepository;
    private final Role USER_ROLE = new Role("ROLE_USER");

    public User findById(Long id){
        return userJpaRepository.findOne(id);
    }

    public boolean addDefaultUserIfNotExists(final String username, final String password, final boolean enabled){
        User user = userJpaRepository.findByUsername(username);
        if(user == null){
            Set<Role> userRoles = new HashSet<>(Arrays.asList(USER_ROLE));
            userJpaRepository.save(new User(username, password, enabled, userRoles));
            return true;
        }
        return false;
    }
}
