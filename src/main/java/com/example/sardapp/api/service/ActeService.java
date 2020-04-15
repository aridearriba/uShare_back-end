package com.example.sardapp.api.service;

import com.example.sardapp.entities.Acte;

import java.util.List;

public interface ActeService {

    public List<Acte> findAll();
    public Acte findById(Integer id);
    //public Acte findByTipus(String tipus);
    public void save(Acte acte);
    public void deleteById(Integer id);

}
