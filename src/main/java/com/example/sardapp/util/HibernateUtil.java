package com.example.sardapp.util;

import com.example.sardapp.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;

public class HibernateUtil
{
    private static ServiceRegistry registry;
    private static SessionFactory sessionFactory;
    private static Configuration configuration;

    public static SessionFactory getSessionFactory()
    {
        if (sessionFactory == null)
        {
            try
            {
                // Create configuration
                configuration = new Configuration().configure().addAnnotatedClass(User.class);
                // Create registry
                registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                // builds a session factory from the service registry
                sessionFactory = configuration.buildSessionFactory(registry);

            } catch (Exception e)
            {
                e.printStackTrace();
                if (registry != null)StandardServiceRegistryBuilder.destroy(registry);
            }
        }
        return sessionFactory;
    }

    public static void shutdown()
    {
        if (registry != null)
        {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}