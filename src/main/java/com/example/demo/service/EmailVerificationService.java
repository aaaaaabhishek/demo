package com.example.demo.service;
import com.example.demo.Payload.UserPayload;
import com.example.demo.Repositary.UserRepositary;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmailVerificationService {

    static final Map<String, String> emailOtpMapping = new HashMap<>();
//  static final Map<String, String> emailOtpMapping = new ConcurrentHashMap<>();

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepositary userRepository;
    @Autowired
    private EmailService emailService;

    private String lastEmail;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public Map<String, String> sendOtpForLogin(String email) {
        if (userService.isEmailVerified(email)) {
            String otp = emailService.generateOtp();
            emailOtpMapping.put(email, otp);

            // Send OTP to the user's email
            emailService.sendOtpEmail(email);

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "OTP sent successfully");
            return response;
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Email is not verified");
            return response;
        }
    }
    public Map<String, String> verifyOtpForLogin(String email, String otp) {
        String storedOtp = emailOtpMapping.get(email);

        Map<String, String> response = new HashMap<>();
        if (storedOtp != null && storedOtp.equals(otp)) {
            emailOtpMapping.remove(email);
            response.put("status", "success");
            response.put("message", "OTP verified successfully");
        } else {
            // Invalid OTP
            response.put("status", "error");
            response.put("message", "Invalid OTP");
        }

        return response;
    }
    User user=new User();
    UserPayload userPayload=new UserPayload();

    public void verifyOtp(String email,String otp) {
        String storedOtp = emailOtpMapping.get(email);
        Map<String, String> response = new HashMap<>();
        if (storedOtp != null && storedOtp.equals(otp)) {
                emailOtpMapping.remove(email);
                lastEmail = email;
               user = userRepository.findByEmail(email);
                userPayload.setEmail(user.getEmail());
                userPayload.setName(user.getName());
                userPayload.setPassword(user.getPassword());
                userPayload.setUsername(user.getUsername());
            }
    }
    public  void update(String password){
        user.setName(userPayload.getName());
        user.setUsername(userPayload.getUsername());
        user.setEmail(userPayload.getEmail());
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
    @Scheduled(fixedDelay = 120000)
    public void expire(){
        emailOtpMapping.remove(lastEmail);
    }
}
