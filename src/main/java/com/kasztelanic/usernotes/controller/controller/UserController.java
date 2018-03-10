package com.kasztelanic.usernotes.controller.controller;

import org.springframework.ui.Model;

import com.kasztelanic.usernotes.persistence.entity.User;

public interface UserController {

	public String index(Model model);

	public String createUser(Model model);

	public String saveUser(User product);

	public String getAllUsers(Model model);

	public String editProduct(Model model, String id);

	public String deleteProduct(String id);
}
