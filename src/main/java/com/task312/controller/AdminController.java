package com.task312.controller;

import com.task312.model.Role;
import com.task312.model.User;
import com.task312.repository.RoleRepository;
import com.task312.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    private UserService userService;
    private RoleRepository roleRepository;
    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping(value = "/admin")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getUserList());
        return "user";
    }

    @GetMapping("/admin/edit")
    public String edit(Model model, @RequestParam(value = "id") long id) {
        model.addAttribute("editUser", userService.showUserById(id));
        List<Role> roles = (List<Role>) roleRepository.findAll();
        model.addAttribute("allRoles", roles);
        return "edit";
    }

    @GetMapping("/admin/new")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("addUser", user);
        List<Role> roles = (List<Role>) roleRepository.findAll();
        model.addAttribute("allRoles", roles);
        return "userAdding";
    }

    @PostMapping("/admin/edit")

    public String update(@ModelAttribute("editUser") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/new")
    public String create(@ModelAttribute("newUser") User user, @ModelAttribute("role") List<Role> roles) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete")
    public String delete(@RequestParam("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
