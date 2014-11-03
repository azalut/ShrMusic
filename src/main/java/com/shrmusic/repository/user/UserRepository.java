package com.shrmusic.repository.user;

import com.shrmusic.entity.user.Role;
import com.shrmusic.entity.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

@Repository
@Transactional
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public User read(long id){
        return entityManager.find(User.class, id);
    }

    public User readByUsername(String username){
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :un", User.class);
        return query.setParameter("un", username).getSingleResult();
    }
}
