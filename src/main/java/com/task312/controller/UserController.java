package com.task312.controller;


import com.task312.repository.RoleRepository;
import com.task312.repository.UserRepository;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
@EnableWebSecurity
@EnableMethodSecurity
public class UserController {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping(value = "/user")
    public String showUserInfo(Model model, Principal principal) {
        model.addAttribute("currentUser", userRepository.findByUsername(principal.getName()).get());
        return "userWelcome";
    }


}
