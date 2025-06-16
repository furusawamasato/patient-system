package com.example.patient_system.controller;

import com.example.patient_system.form.UserForm;
import com.example.patient_system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final UserService userService;
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "login/register";
    }
    @PostMapping("/register")
    public String registerUser(UserForm userForm) {
        userService.createUser(userForm);
        return "redirect:/login?register";
    }
}
