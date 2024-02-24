package com.example.demo.service.Impl;

import com.example.demo.Payload.Contact_us_dto;
import com.example.demo.Repositary.Contact_us_Repositary;
import com.example.demo.entity.Contact_us;
import com.example.demo.service.Contact_us_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Contact_us_ServiceImpl implements Contact_us_Service {
    @Autowired
    private Contact_us_Repositary contact_us_Repo;
    @Override
    public void sendData(Contact_us_dto contactdto) {
        Contact_us contact=new Contact_us();
        contact.setEmail(contactdto.getEmail());
        contact.setName(contactdto.getName());
        contact.setMessage(contactdto.getMessage());
        contact_us_Repo.save(contact);
    }
}
