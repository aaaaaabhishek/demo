package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.example.demo.service.EmailVerificationService.emailOtpMapping;
@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    private final UserService userService;
    @Autowired
    public EmailService(JavaMailSender javaMailSender, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
    }
    public String generateOtp() {
        return String.format("%06d", new java.util.Random().nextInt(1000000));
    }
    public void sendOtpEmail(String email) {
        String otp = generateOtp();
        emailOtpMapping.put(email,otp);

        sendEmail(email, "OTP for Email Verification", "Your OTP is: "+otp);//371913

    }
    private void sendEmail(String to, String subject,String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("your.gmail@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

}