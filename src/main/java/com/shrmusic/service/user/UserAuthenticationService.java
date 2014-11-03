package com.shrmusic.service.user;

import com.shrmusic.entity.user.Role;
import com.shrmusic.entity.user.User;
import com.shrmusic.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service(value = "userAuthenticationService")
@Transactional
public class UserAuthenticationService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override //TODO: make an assemblage class, shorten this method to maximum
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.readByUsername(username);

        final Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        Set<Role> roles = user.getRoles();
        roles.stream().forEach(el -> authorities.add(new SimpleGrantedAuthority(el.getRole())));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isEnabled(),
                user.isEnabled(),
                user.isEnabled(),
                authorities
        );
    }
}
