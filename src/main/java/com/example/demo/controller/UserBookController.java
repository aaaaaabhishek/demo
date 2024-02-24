package com.example.demo.controller;

import com.example.demo.Payload.UserBookDto;
import com.example.demo.service.UserBookservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@Controller
public class UserBookController {
    @Autowired
    private UserBookservice userBookservice;
    @GetMapping("/home")
    public String s() {return "Home";}
    @GetMapping("/Contact")
    public String contact(){return "Contact";}
    @GetMapping("/signin")
    public String Loginpage() {
        return "Login";
    }
    @GetMapping("/signup")
    public String ss(){
        return "signupBookmytrip";
    }
    @GetMapping("/forget")
    public String fforgetpassword(){
        return "forgetpassword";
    }
    @GetMapping("/forgetpassword")
    public String forgetPassword(){
        return "OTP-Reg";
    }
    @GetMapping("/service")
    public String Service(){
        return "Service";
    }
    @GetMapping("/standard")
    public String Service_Standard(){
        return "Standard";
    }
    @GetMapping("/deluxe")
    public String Service_deluxe(){return "deluxe";}
    @GetMapping("/suite")
    public String Service_Suite(){
        return "suite";
    }
    @GetMapping("/Logout")
    public String Logout(){return "Logout";}
    @GetMapping("/admin")
    public String Admin_menu(){return "Admin";}
    @PostMapping("/bookings")
    public String saveUser(@RequestParam("check_In_Date") String check_In_Date,@RequestParam("check_Out_Date") String check_Out_Date ,@RequestParam("Room_Type") String Room_Type,@RequestParam("number_of_Guests") String number_of_Guests){
        UserBookDto userbookdto=new UserBookDto();
        userbookdto.setCheck_In_Date(check_In_Date);
        userbookdto.setRoom_Type(Room_Type);
        userbookdto.setCheck_Out_Date(check_Out_Date);
        userbookdto.setNumber_of_Guests(number_of_Guests);
        userBookservice.saveuser(userbookdto);
        return "Home";
    }
}
