package com.kasztelanic.usernotes.controller.controllerimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kasztelanic.usernotes.controller.controller.UserController;
import com.kasztelanic.usernotes.persistence.entity.User;
import com.kasztelanic.usernotes.service.service.UserService;

@Controller
public class UserControllerImpl implements UserController {

	@Autowired
	private UserService userService;

	@Override
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("users", userService.findAll());
		return "index";
	}

	@Override
	@RequestMapping(path = "/users/add", method = RequestMethod.GET)
	public String createUser(Model model) {
		model.addAttribute("user", new User());
		return "edit";
	}

	@Override
	@RequestMapping(path = "users", method = RequestMethod.POST)
	public String saveUser(User user) {
		userService.save(user);
		return "redirect:/";
	}

	@Override
	@RequestMapping(path = "/users", method = RequestMethod.GET)
	public String getAllUsers(Model model) {
		model.addAttribute("users", userService.findAll());
		return "index";
	}

	@Override
	@RequestMapping(path = "/users/edit/{id}", method = RequestMethod.GET)
	public String editProduct(Model model, @PathVariable(value = "id") String id) {
		model.addAttribute("user", userService.findOne(id));
		return "edit";
	}

	@Override
	@RequestMapping(path = "/users/delete/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable(name = "id") String id) {
		userService.delete(id);
		return "redirect:/";
	}
}
