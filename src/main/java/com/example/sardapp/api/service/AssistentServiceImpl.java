package com.example.sardapp.api.service;

import com.example.sardapp.api.dao.AssistantDAO;
import com.example.sardapp.api.dao.UserDAO;
import com.example.sardapp.entities.Assistent;
import com.example.sardapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistentServiceImpl implements AssistentService
{
    @Autowired
    private AssistantDAO assistantDAO;

    @Override
    public Assistent findById(Integer id, String email)
    {
       return assistantDAO.findById(id, email);
    }

    @Override
    public List<User> getAssistants(Integer id)
    {
        List<User> listUsers = assistantDAO.getAssistants(id);
        return listUsers;
    }

    @Override
    public Boolean newAssistant(Integer id, String email)
    {
        return assistantDAO.newAssistant(id, email);
    }

    @Override
    public Boolean deleteAssistant(Integer id, String email)
    {
        return assistantDAO.deleteAssistant(id, email);
    }
}
