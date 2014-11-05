package com.shrmusic.repository.user;

import com.shrmusic.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
