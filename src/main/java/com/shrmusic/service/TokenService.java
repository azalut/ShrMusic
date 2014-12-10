package com.shrmusic.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {
    public boolean validate(String token);
    public UserDetails getUserFromToken(String token);
}
