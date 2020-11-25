package com.backend.challenge.repository.config;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Stream;

public class GenericDao<T, U> {

    protected U save(T entity) {
        U id = null;
        Transaction transaction = null;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            id = (U) session.save(entity);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return id;
    }

    protected Stream<T> listFromMessagesIds(List<Integer> ids, Class<T> cl) {
        Session session = HibernateUtil.getSessionFactory().openSession();
      
        return session.createQuery("from " + cl.getName()  + " where id in :value", cl).setParameter("value", ids).list().stream();
    }
}
