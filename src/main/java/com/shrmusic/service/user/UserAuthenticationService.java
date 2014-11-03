package com.shrmusic.service.user;

import com.shrmusic.entity.user.User;
import com.shrmusic.repository.user.UserRepository;
import com.shrmusic.util.user.UserDetailsAssemblage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "userAuthenticationService")
@Transactional
public class UserAuthenticationService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsAssemblage assemblage;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.readByUsername(username);
        return assemblage.assembleUser(user);
    }
}
