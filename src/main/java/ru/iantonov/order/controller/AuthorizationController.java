package ru.iantonov.order.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iantonov.order.domain.User;
import ru.iantonov.order.repository.UserRepository;

@Controller
public class AuthorizationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthorizationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login(){
        return "loginPage";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam String jLogin, @RequestParam String jPassword){
        userRepository.save(new User(jLogin, passwordEncoder.encode(jPassword)));
        return "loginPage";
    }
}