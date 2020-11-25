package com.backend.challenge.repository.impl;

import java.util.*;

import com.backend.challenge.repository.TokenRepository;

import com.backend.challenge.repository.config.GenericDao;
import com.backend.challenge.repository.config.HibernateUtil;

import com.backend.challenge.repository.model.Token;
import com.backend.challenge.repository.model.User;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.Session;

//Ok I guess this is fine
@Slf4j
public class TokenRepositoryImpl extends GenericDao<Token, String> implements TokenRepository {

    @Override
    public Optional<Token> findByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from Token where user = :value", Token.class).setParameter("value", user).uniqueResultOptional();
    }

    @Override
    public String save(Token token) {
        return super.save(token);
    }

    @Override
    public Boolean existsById(String token) {
        return findById(token).isPresent();
    }

    @Override
    public Optional<Token> findById(String token) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from Token where token = :value", Token.class).setParameter("value", token).uniqueResultOptional();
    }
}
