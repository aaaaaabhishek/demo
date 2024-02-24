package com.example.demo.controller;
import com.example.demo.Payload.LoginDto;
import com.example.demo.Payload.SignupDto;
import javax.servlet.http.HttpServletRequest;
import com.example.demo.Repositary.RoleRepositary;
import com.example.demo.Repositary.UserRepositary;
import com.example.demo.Security.JwtTokenProvider;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Collections;
@Controller
//@Scope("prototype")
    public class LoginController {
    @Autowired
    private UserRepositary userRepositary;
    @Autowired
private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepositary roleRepositary;
    @Autowired
    private JwtTokenProvider tokenProvider;
//    @Autowired
//    private Userservice Userservice;

//    @GetMapping("/signupPage")
//    public String homePage() {
//        return "signupBookmytrip";
//    }
//    @GetMapping("/Login")
//    public String Login() {
//        return "Login";
//    }
    @PostMapping("/signin")
    public String authenticateuser(@RequestParam("usernameOrEmail") String usernameOrEmail,
                                   @RequestParam("password") String password,HttpSession session) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usernameOrEmail, password)
        );
        String loginId = authentication.getName();
        session.setAttribute("loginId", loginId);
        //authenticate method compare actual value with expected value
           SecurityContextHolder.getContext().setAuthentication(authentication);
          //get token from tokenProvider
          String token=tokenProvider.generateToken(authentication);
        return "Home";
        }

//    @PostMapping("/signup")
//    public RedirectView registerUser(@RequestParam String name,
//                                     @RequestParam String username,
//                                     @RequestParam String email,
//                                     @RequestParam String password) {
//        if (userRepositary.existsByEmail(email)) {
//            return new RedirectView("/signin?error=email");
//        }
//
//        if (userRepositary.existsByUsername(username)) {
//            return new RedirectView("/signin?error=username");
//        }
//
//        User user = new User();
//        user.setName(name);
//        user.setUsername(username);
//        user.setEmail(email);
//        user.setPassword(passwordEncoder.encode(password));
//
//        Role roles = roleRepositary.findByName("ROLE_ADMIN")
//                .orElseThrow(() -> new RuntimeException("Role not found: ROLE_ADMIN"));
//
//        user.setRoles(Collections.singleton(roles));
//        User savedUser = userRepositary.save(user);
//
//       // return new RedirectView("/signin?signup=success");
//        return  new RedirectView("Login");
//    }
@PostMapping("/signup")
public String registerUser(@RequestParam String name,
                                 @RequestParam String username,
                                 @RequestParam String email,
                                 @RequestParam String password) {
    if (userRepositary.existsByEmail(email)) {
      //  return new RedirectView("/signin?error=email");
    }

    if (userRepositary.existsByUsername(username)) {
      //  return new RedirectView("/signin?error=username");
    }

    User user = new User();
    user.setName(name);
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password));

    Role roles = roleRepositary.findByName("ROLE_ADMIN")
            .orElseThrow(() -> new RuntimeException("Role not found: ROLE_ADMIN"));

    user.setRoles(Collections.singleton(roles));
    User savedUser = userRepositary.save(user);

    // return new RedirectView("/signin?signup=success");
    return ("Login");
}
    }
