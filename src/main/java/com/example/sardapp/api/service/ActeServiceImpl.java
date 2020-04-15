package com.example.sardapp.api.service;

import com.example.sardapp.api.dao.ActeDAO;
import com.example.sardapp.entities.Acte;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ActeServiceImpl implements ActeService {

    @Autowired
    private ActeDAO acteDAO;

    @Override
    public List<Acte> findAll() {
        List<Acte> listActes = acteDAO.findAll();
        return listActes;
    }

    @Override
    public Acte findById(Integer id) {
        Acte acte = acteDAO.findById(id);
        return acte;
    }

    @Override
    public void save(Acte acte) {
        acteDAO.save(acte);
    }

    @Override
    public void deleteById(Integer id) {
        acteDAO.deleteById(id);
    }
}
