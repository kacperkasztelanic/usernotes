package com.kasztelanic.usernotes.controller.controller;

import org.springframework.ui.Model;

import com.kasztelanic.usernotes.persistence.entity.User;

public interface UserController {

	String findAllUsers(Model model);

	String createUser(Model model);

	String updateUser(Model model, String id);

	String deleteUser(String id);

	String saveUser(User product);
}
