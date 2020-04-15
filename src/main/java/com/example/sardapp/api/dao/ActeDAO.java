package com.example.sardapp.api.dao;

import java.io.IOException;
import java.util.List;

import com.example.sardapp.entities.Acte;

public interface ActeDAO {

    public List<Acte> findAll();
    public Acte findById(Integer id);
    //public Acte findByTipus(String tipus);
    public boolean save(Acte acte);
    public boolean deleteById(Integer id);

}
