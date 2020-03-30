package com.kasztelanic.usernotes.controller.impl;

import com.kasztelanic.usernotes.controller.UserController;
import com.kasztelanic.usernotes.persistence.entity.User;
import com.kasztelanic.usernotes.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping({ "/", "/users" })
    public String findAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "index";
    }

    @Override
    @GetMapping("/users/add")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "edit";
    }

    @Override
    @GetMapping("/users/edit/{id}")
    public String updateUser(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("user", userService.findOne(id));
        return "edit";
    }

    @Override
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") String id) {
        userService.delete(id);
        return "redirect:/";
    }

    @Override
    @PostMapping("users")
    public String saveUser(User user) {
        userService.save(user);
        return "redirect:/";
    }
}
