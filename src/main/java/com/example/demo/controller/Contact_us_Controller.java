package com.example.demo.controller;

import com.example.demo.Payload.Contact_us_dto;
import com.example.demo.service.Contact_us_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Contact_us_Controller {
    @Autowired
    private Contact_us_Service Contact_us_Service;
    @PostMapping("/Contact-us")
    public String sendData(@RequestParam("name")  String name,@RequestParam("email") String email ,@RequestParam("message")String message){
        Contact_us_dto contactdto=new Contact_us_dto();
        contactdto.setName(name);
        contactdto.setEmail(email);
        contactdto.setMessage(message);
        Contact_us_Service.sendData(contactdto);
        return "Home";
    }
}
