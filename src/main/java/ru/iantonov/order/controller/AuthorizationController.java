package ru.iantonov.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iantonov.order.domain.User;
import ru.iantonov.order.repository.UserRepository;

@Controller
public class AuthorizationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(){
        return "loginPage";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam String j_login, @RequestParam String j_password){
        userRepository.save(new User(j_login, passwordEncoder.encode(j_password)));
        return "loginPage";
    }
}