package com.task312.controller;

import com.task312.model.Role;
import com.task312.model.User;
import com.task312.repository.RoleRepository;
import com.task312.repository.UserRepository;
import com.task312.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

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
    @PostMapping("/admin/edit")

    public String update(@ModelAttribute("editUser") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/new")
    public String create(@ModelAttribute("newUser") User user,  @RequestParam(value = "checkedRoles") String[] selectResult) {
        for (String s : selectResult) {
            user.addRole(roleRepository.findByRole("ROLE_" + s));
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete")
    public String delete(@RequestParam("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
