package com.example.sardapp.api.dao;

import com.example.sardapp.api.session.AbstractSession;
import com.example.sardapp.entities.Acte;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.List;

public class ActeDAOImpl extends AbstractSession implements ActeDAO {
    @Override
    public List<Acte> findAll() {
        return getSession().createQuery("from Acte").list();
    }

    @Override
    public Acte findById(Integer id) {
        return getSession().get(Acte.class, id);
    }

    @Override
    public boolean save(Acte acte) {
        getSession().beginTransaction();
        getSession().saveOrUpdate(acte);
        getSession().getTransaction().commit();
        return getSession().getTransaction().getStatus() == TransactionStatus.COMMITTED;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean result = false;
        Acte acte = findById(id);
        if(acte != null) {
            getSession().beginTransaction();
            getSession().delete(acte);
            getSession().getTransaction().commit();
            result = getSession().getTransaction().getStatus() == TransactionStatus.COMMITTED;
        }
        return result;
    }
}
