package com.example.demo.service;

import com.example.demo.Repositary.UserRepositary;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Forget_USerService {
    @Autowired
    private UserRepositary userRepositary;

    public User registerUser(User user) {
        // Save the user to the database
        return userRepositary.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepositary.findByEmail(email);
    }

    public void verifyEmail(User user) {
        user.setEmailVerified(true);
        userRepositary.save(user);
    }

    public boolean isEmailVerified(String email) {
        User user = userRepositary.findByEmail(email);
        return user != null && user.isEmailVerified();
    }
}
