package com.example.sardapp.api.dao;

import java.util.List;

import com.example.sardapp.api.session.AbstractSession;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.stereotype.Repository;

import com.example.sardapp.entities.User;

@Repository
public class UserDAOImpl extends AbstractSession implements UserDAO
{
    @Override
    public List<User> findAll()
    {
        return getSession().createQuery("from User").list();
    }

    @Override
    public User findByUsername(String username)
    {
        return getSession().get(User.class, username);
    }

    @Override
    public boolean save(User user)
    {
        getSession().beginTransaction();
        getSession().save(user);
        getSession().getTransaction().commit();
        return getSession().getTransaction().getStatus() == TransactionStatus.COMMITTED;
    }

    @Override
    public boolean deleteByUsername(String username)
    {
        boolean result = false;
        User user = findByUsername(username);
        if(user != null) {
            getSession().beginTransaction();
            getSession().delete(user);
            getSession().getTransaction().commit();
            result = getSession().getTransaction().getStatus() == TransactionStatus.COMMITTED;
        }
        return result;
    }
}
