package com.backend.challenge.repository.impl;

import java.util.*;

import com.backend.challenge.repository.UserRepository;
import com.backend.challenge.repository.config.GenericDao;
import com.backend.challenge.repository.config.HibernateUtil;
import com.backend.challenge.repository.model.User;

import com.backend.challenge.service.exception.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;


//Ok I guess this is fine
@Slf4j
public class UserRepositoryImpl extends GenericDao<User, Integer> implements UserRepository {

    @Override
    public Optional<User> findByUsernameAndAndPassword(String username, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from User where username = :value1 and password = :value2", User.class).setParameter("value1", username).setParameter("value2", password).uniqueResultOptional();
    }

    @Override
    public Boolean existsByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return !session.createQuery("from User where username = :value", User.class).setParameter("value", username).list().isEmpty();
        }
    }

    @Override
    public Integer save(User user) {
        return super.save(user);
    }

    @Override
    public User get(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            if (user == null) throw new UserNotFoundException();
            return user;
        }
    }
}
