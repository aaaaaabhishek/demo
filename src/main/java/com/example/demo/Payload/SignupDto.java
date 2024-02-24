package com.example.demo.Payload;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
    public class SignupDto {
        private String name;
        private  String username;
        private String email;
        private String password;
    }

