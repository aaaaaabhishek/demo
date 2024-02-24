package com.example.demo.controller;

import com.example.demo.Payload.UserPayload;
import com.example.demo.Repositary.UserRepositary;
import com.example.demo.entity.User;
import com.example.demo.service.EmailService;
import com.example.demo.service.EmailVerificationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class ForgetPasswordController {

    private UserService userService;

    private EmailService emailService;
    private EmailVerificationService emailVerificationService;

    public ForgetPasswordController(UserService userService, EmailService emailService,EmailVerificationService emailVerificationService) {
        this.userService = userService;
        this.emailService = emailService;
        this.emailVerificationService = emailVerificationService;
    }
    private UserRepositary userRepositary;
    UserPayload userPayload=new UserPayload();

    @PostMapping("/registerupdate")
    public String registerUserupdate(@RequestParam("email") String email) {
      if (userRepositary.existsByEmail("email")){
          userPayload.setUserSetEmail(email);
          emailService.sendOtpEmail(email);
          return "OTP-Reg";

      }
      return "Home";
    }
    @PostMapping("/verify-otp-update")
    public String verifyOtpUpdate( @RequestParam("otp") String otp) {
        emailVerificationService.verifyOtp(userPayload.getUserSetEmail(),otp);
        return "ChangePassword";
    }
    @PostMapping("/update_password")
    public String PasswordChnage(@RequestParam("confirmPassword") String password){
        emailVerificationService.update(password);
        return "Home";
    }

}