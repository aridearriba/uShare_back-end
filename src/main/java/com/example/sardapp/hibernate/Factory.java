package com.example.sardapp.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class Factory
{
    private Factory()
    {
        throw new IllegalStateException("Utility class");
    }

    public static SessionFactory getSessionFactory(Class tipoEntity){
        File f = HibernateUtil.getConfigFile();
        return new Configuration().configure(f).addAnnotatedClass(tipoEntity).buildSessionFactory();
    }

}
