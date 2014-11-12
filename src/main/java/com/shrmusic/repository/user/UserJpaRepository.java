package com.shrmusic.repository.user;

import com.shrmusic.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
