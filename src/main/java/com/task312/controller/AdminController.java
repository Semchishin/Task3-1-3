package com.task312.controller;

import com.task312.model.User;
import com.task312.repository.RoleRepository;
import com.task312.repository.UserRepository;
import com.task312.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class AdminController {

    private UserService userService;
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public AdminController(UserService userService, RoleRepository roleRepository, UserRepository userRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/admin")
    public String showAllUsers(Model model, Principal principal) {
        model.addAttribute("users", userService.getUserList());
        model.addAttribute("currentUser", userRepository.findByUsername(principal.getName()).get());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("newUser", new User());
        return "admin";
    }
    @GetMapping ("/admin/updateUser/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("admin/updateUser/{id}")
    public String updateUser(@ModelAttribute("newUser") User user, @PathVariable("id") Long id) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/new")
    public String create(@ModelAttribute("newUser") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/new")
    public String addNewUser(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        model.addAttribute("user", userRepository.findById(user.getId()));
        model.addAttribute("role", user.getRoles());
        return "/admin";
    }
}
