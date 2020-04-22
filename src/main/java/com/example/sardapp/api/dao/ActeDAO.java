package com.example.sardapp.api.dao;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.example.sardapp.entities.Acte;

public interface ActeDAO {

    List<Acte> findAll();
    List<Acte> findAllByTipus(String tipus);
    List<Acte> findAllByDia(Date dia);
    List<Acte> findAllCanceled();
    List<Acte> findAllByComarca(String comarca);
    List<Acte> findAllByTerritori(String territori);
    List<Acte> findAllByPoblacioMitjana(String poblacioMitjana);

    Acte findById(Integer id);

    boolean save(Acte acte);
    boolean deleteById(Integer id);
    boolean existsById(Integer id);

}
