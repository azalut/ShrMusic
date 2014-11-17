package com.shrmusic.repository.user;

import com.shrmusic.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
