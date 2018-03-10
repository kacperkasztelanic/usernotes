package com.kasztelanic.usernotes.controller.controllerimpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kasztelanic.usernotes.persistence.entity.Note;
import com.kasztelanic.usernotes.persistence.entity.User;
import com.kasztelanic.usernotes.service.service.UserService;

@Controller
public class NotesControllerImpl {

	@Autowired
	private UserService userService;

	@RequestMapping(path = "/notes/{id}", method = RequestMethod.GET)
	public String getNotesForUser(Model model, @PathVariable(value = "id") String id) {
		model.addAttribute("usernotes", userService.findOne(id).getNotes());
		model.addAttribute("userid", id);
		return "usernotes";
	}

	@RequestMapping(path = "/notes/delete/{userid}/{noteid}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable(name = "userid") String userid,
			@PathVariable(name = "noteid") String noteid) {
		User user = userService.findOne(userid);
		user.getNotes().removeIf(n -> n.getUuid().equals(noteid));
		userService.save(user);
		return "redirect:/notes/" + userid;
	}

	@RequestMapping(path = "/notes/add/{userid}", method = RequestMethod.GET)
	public String createNote(@PathVariable(name = "userid") String userid, Model model) {
		model.addAttribute("note", new Note());
		model.addAttribute("userid", userid);
		return "editnote";
	}

	@RequestMapping(path = "notes/{userid}", method = RequestMethod.POST)
	public String saveUser(@PathVariable(name = "userid") String userid, Note note) {
		User user = userService.findOne(userid);
		Set<Note> notes = user.getNotes();
		if (notes.contains(note)) {
			notes.remove(note);
		}
		notes.add(note);
		userService.save(user);
		return "redirect:/notes/" + userid;
	}

	@RequestMapping(path = "/notes/edit/{userid}/{noteid}", method = RequestMethod.GET)
	public String editProduct(Model model, @PathVariable(value = "userid") String userid,
			@PathVariable(value = "noteid") String noteid) {
		User user = userService.findOne(userid);
		Set<Note> notes = user.getNotes();
		model.addAttribute("userid", userid);
		model.addAttribute("note", notes.stream().filter(n -> n.getUuid().equals(noteid)).findFirst().get());
		return "editnote";
	}

}
