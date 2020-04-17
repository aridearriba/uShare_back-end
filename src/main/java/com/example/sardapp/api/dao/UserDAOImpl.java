package com.example.sardapp.api.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.sardapp.api.session.AbstractSession;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.stereotype.Repository;

import com.example.sardapp.entities.User;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class UserDAOImpl extends AbstractSession implements UserDAO
{
    @Override
    public List<User> findAll()
    {
        return getSession().createQuery("from User").list();
    }

    @Override
    public User findByEmail(String email)
    {
        return getSession().get(User.class, email);
    }

    @Override
    public boolean save(User user)
    {
        getSession().beginTransaction();
        getSession().saveOrUpdate(user);
        getSession().getTransaction().commit();
        return getSession().getTransaction().getStatus() == TransactionStatus.COMMITTED;
    }

    @Override
    public boolean deleteByEmail(String email)
    {
        boolean result = false;
        User user = findByEmail(email);
        if(user != null) {
            getSession().beginTransaction();
            getSession().delete(user);
            getSession().getTransaction().commit();
            result = getSession().getTransaction().getStatus() == TransactionStatus.COMMITTED;
        }
        return result;
    }

    @Override
    public boolean addProfileImage(String email, byte[] image) throws IOException
    {
        // Update image in user
        User user = findByEmail(email);
        user.setImage(image);
        getSession().beginTransaction();
        getSession().saveOrUpdate(user);
        getSession().getTransaction().commit();
        return getSession().getTransaction().getStatus() == TransactionStatus.COMMITTED;
    }

}
