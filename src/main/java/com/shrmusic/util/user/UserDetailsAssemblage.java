package com.shrmusic.util.user;

import com.shrmusic.entity.user.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserDetailsAssemblage {
    public org.springframework.security.core.userdetails.User assembleUser(User user){
        Set<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(p ->
                        new SimpleGrantedAuthority(p.getRole())
        ).collect(Collectors.toSet());

        boolean enabled = user.isEnabled();
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                enabled,
                enabled,
                enabled,
                enabled,
                authorities
        );
    }

}
