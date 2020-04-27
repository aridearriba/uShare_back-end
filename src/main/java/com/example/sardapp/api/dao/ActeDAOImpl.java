package com.example.sardapp.api.dao;

import com.example.sardapp.api.session.AbstractSession;
import com.example.sardapp.entities.Acte;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActeDAOImpl extends AbstractSession implements ActeDAO {
    @Override
    public List<Acte> findAll() {
        return getSession().createQuery("FROM Acte").list();
    }

    @Override
    public List<Acte> findByFilters(List<String> tipus, Boolean diaConcret, Date dia, String hora, Boolean anul,
                                    List<String> comarca, List<String> territori, List<String> cobla, List<String> poblacioMitjana) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Acte> query = cb.createQuery(Acte.class);
        Root<Acte> acts = query.from(Acte.class);

        List<Predicate> predicates = new ArrayList<>();

        if (tipus != null)
        {
            for (String type : tipus)
            {
                predicates.add(cb.like(acts.get("tipus"), type));
            }
        }

        if (dia != null)
        {
            if (diaConcret)
            {
                predicates.add(cb.equal(acts.get("dia"), dia));
            }
            else
            {
                predicates.add(cb.greaterThanOrEqualTo(acts.get("dia"), dia));
            }
        }

        if (hora != null)
        {
            predicates.add(cb.like(acts.get("hora1"), hora));
            predicates.add(cb.like(acts.get("hora2"), hora));
            predicates.add(cb.like(acts.get("hora3"), hora));
        }

        if (anul)
        {
            predicates.add(cb.isNotNull(acts.get("anul")));
        }

        if (comarca != null)
        {
            for (String region : comarca)
            {
                predicates.add(cb.like(acts.get("comarca"), region));
            }
        }

        if (territori != null)
        {
            for (String territory : territori)
            {
                predicates.add(cb.like(acts.get("territori"), territory));
            }
        }

        if (cobla != null)
        {
            for (String cobles: cobla)
            {
                predicates.add(cb.like(acts.get("cobla1"), cobles));
                predicates.add(cb.like(acts.get("cobla2"), cobles));
                predicates.add(cb.like(acts.get("cobla3"), cobles));
                predicates.add(cb.like(acts.get("cobla4"), cobles));
                predicates.add(cb.like(acts.get("cobla5"), cobles));
                predicates.add(cb.like(acts.get("cobla6"), cobles));
                predicates.add(cb.like(acts.get("cobla7"), cobles));
            }
        }

        if (poblacioMitjana != null)
        {
            for (String population : poblacioMitjana)
            {
                predicates.add(cb.like(acts.get("poblacioMitjana"), population));
            }
        }


        query.select(acts).where(predicates.toArray(new Predicate[predicates.size()]));
        List<Acte> listActs = getSession().createQuery(query).list();
        return listActs;
    }

    @Override
    public List<Acte> findAllByTipus(String tipus) {
        return getSession().createQuery("FROM Acte WHERE tipus = '"+tipus+"'").list();
    }

    @Override
    public List<Acte> findAllByDia(Date dia) {
        return getSession().createQuery("FROM Acte WHERE dia = '"+dia+"'").list();
    }

    @Override
    public List<Acte> findAllCanceled() {
        return getSession().createQuery("FROM Acte WHERE anul IS NOT NULL").list();
    }

    @Override
    public List<Acte> findAllByComarca(String comarca) {
        return getSession().createQuery("FROM Acte WHERE comarca = '"+comarca+"'").list();
    }

    @Override
    public List<Acte> findAllByTerritori(String territori) {
        return getSession().createQuery("FROM Acte WHERE territori = "+territori+"'").list();
    }

    @Override
    public List<Acte> findAllByPoblacioMitjana(String poblacioMitjana) {
        return getSession().createQuery("FROM Acte WHERE poblacioMitjana = "+poblacioMitjana+"'").list();
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
