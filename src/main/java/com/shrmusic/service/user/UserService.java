package com.shrmusic.service.user;

import com.shrmusic.entity.user.Role;
import com.shrmusic.entity.user.User;
import com.shrmusic.repository.user.UserJpaRepository;
import com.shrmusic.util.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserJpaRepository userJpaRepository;
    private final RoleEnum role = RoleEnum.ROLE_USER;

    public User findById(Long id){
        return userJpaRepository.findOne(id);
    }

    public User findByUsername(String username){
        return userJpaRepository.findByUsername(username);
    }

    public boolean addDefaultUserIfNotExists(final String username, final String password, final boolean enabled){
        User user = userJpaRepository.findByUsername(username);
        if(user == null){
            Set<Role> userRoles = new HashSet<Role>(Arrays.asList(role.getRole()));
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            userJpaRepository.save(new User(username, md5Password, enabled, null, userRoles));
            return true;
        }
        return false;
    }

    @Transactional
    public void addRole(final Long id, Role role){
        User user = userJpaRepository.getOne(id);
        Set<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);
    }
}
