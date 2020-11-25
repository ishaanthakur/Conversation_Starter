package com.backend.challenge.repository.impl;

import com.backend.challenge.repository.MessageRepository;
import com.backend.challenge.repository.config.GenericDao;
import com.backend.challenge.repository.config.HibernateUtil;
import com.backend.challenge.repository.model.Message;
import com.backend.challenge.repository.model.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import java.util.*;

@Slf4j
public class MessageRepositoryImpl extends GenericDao<Message, Integer> implements MessageRepository {

    @Override
    public Integer save(Message message) {
        return super.save(message);
    }

    @Override
    public List<Message> listMessages(User sender, User recipient, Integer start, Integer limit) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from Message where sender = :value1 and recipient = :value2", Message.class).setParameter("value1", sender).setParameter("value2", recipient).setMaxResults(limit).setFirstResult(start > 0 ? start-1 : start).list();
    }
}
