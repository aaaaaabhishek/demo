package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {
private HttpServletRequest request;
    LogoutController(HttpServletRequest request){
        this.request=request;
    }
    @PostMapping("/Logout")
    public String Logout() {
        HttpSession session = request.getSession(false);//it check any session is active otherwise set null
        if (session != null) {
            session.invalidate();
        }
        return "login";
    }
}
