package com.kasztelanic.usernotes.controller;

import com.kasztelanic.usernotes.persistence.entity.User;

import org.springframework.ui.Model;

public interface UserController {

    String findAllUsers(Model model);

    String createUser(Model model);

    String updateUser(Model model, String id);

    String deleteUser(String id);

    String saveUser(User product);
}
