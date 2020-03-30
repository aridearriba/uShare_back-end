package com.example.sardapp;

import com.example.sardapp.hibernate.Factory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.sardapp.entities.User;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Runner
{
    public static void main(String[] args)
    {
        User user = new User("user", "user123", "user@gmail.com");
        User user1 = new User("user1", "pswd", "user1@gmail.com");

        Transaction transaction = null;

        try (Session session = Factory.getSessionFactory(User.class).openSession())
        {
            // start a transaction
            transaction = session.beginTransaction();

            // save the student objects
            session.save(user);
            session.save(user1);

            // commit transaction
            transaction.commit();
        } catch (Exception e)
        {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        try (Session session = Factory.getSessionFactory(User.class).openSession())
        {
            List<User> users = session.createQuery("from User", User.class).list();
            users.forEach(u -> System.out.println(u.getUsername()));
        } catch (Exception e)
        {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}

