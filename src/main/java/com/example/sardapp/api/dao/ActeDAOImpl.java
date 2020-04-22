package com.example.sardapp.api.dao;

import com.example.sardapp.api.session.AbstractSession;
import com.example.sardapp.entities.Acte;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class ActeDAOImpl extends AbstractSession implements ActeDAO {
    @Override
    public List<Acte> findAll() {
        return getSession().createQuery("FROM Acte").list();
    }

    @Override
    public List<Acte> findAllByTipus(String tipus) {
        return getSession().createQuery("FROM Acte WHERE tipus ="+tipus).list();
    }

    @Override
    public List<Acte> findAllByDia(Date dia) {
        return getSession().createQuery("FROM Acte WHERE dia ="+dia).list();
    }

    @Override
    public List<Acte> findAllCanceled() {
        return getSession().createQuery("FROM Acte WHERE anul IS NOT NULL").list();
    }

    @Override
    public List<Acte> findAllByComarca(String comarca) {
        return getSession().createQuery("FROM Acte WHERE comarca = "+comarca).list();
    }

    @Override
    public List<Acte> findAllByTerritori(String territori) {
        return getSession().createQuery("FROM Acte WHERE territori = "+territori).list();
    }

    @Override
    public List<Acte> findAllByPoblacioMitjana(String poblacioMitjana) {
        return getSession().createQuery("FROM Acte WHERE poblacioMitjana = "+poblacioMitjana).list();
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
        boolean exists = existsById(id);
        if(exists) {
            Acte acte = findById(id);
            getSession().beginTransaction();
            getSession().delete(acte);
            getSession().getTransaction().commit();
            exists = getSession().getTransaction().getStatus() == TransactionStatus.COMMITTED;
        }
        return exists;
    }

    @Override
    public boolean existsById(Integer id) {
        boolean result = false;
        Acte acte = findById(id);
        if(acte != null) {
            result = true;
        }
        return result;
    }
}
