package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.impl.RoleService;
import ru.itmentor.spring.boot_security.demo.service.impl.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    private static final String USER = "ROLE_USER";

    @GetMapping()
    public String registration(ModelMap model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, ModelMap model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (!userService.getByParam(userForm.getUsername()).isEmpty()) {
            model.addAttribute("message", "User exists!");
            return registration(model);
        }
        
        userForm.addRole(roleService.getByParam(USER).orElseThrow(()->new RuntimeException("Role not found")));
        userService.save(userForm);

        return "redirect:/";
    }
}