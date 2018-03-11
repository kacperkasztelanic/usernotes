package com.kasztelanic.usernotes.controller.controllerimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kasztelanic.usernotes.controller.controller.NotesController;
import com.kasztelanic.usernotes.persistence.entity.Note;
import com.kasztelanic.usernotes.service.service.UserService;

@Controller
public class NotesControllerImpl implements NotesController {

    @Autowired
    private UserService userService;

    @Override
    @RequestMapping(path = "/notes/{userid}", method = RequestMethod.GET)
    public String findNotesForUser(Model model, @PathVariable(value = "userid") String userId) {
	model.addAttribute("usernotes", userService.findOne(userId).getNotes());
	model.addAttribute("user", userService.findOne(userId));
	return "usernotes";
    }

    @Override
    @RequestMapping(path = "/notes/add/{userid}", method = RequestMethod.GET)
    public String createNote(Model model, @PathVariable(name = "userid") String userId) {
	model.addAttribute("note", new Note());
	model.addAttribute("userid", userId);
	return "editnote";
    }

    @Override
    @RequestMapping(path = "/notes/edit/{userid}/{noteid}", method = RequestMethod.GET)
    public String updateNote(Model model, @PathVariable(value = "userid") String userId,
	    @PathVariable(value = "noteid") String noteId) {
	model.addAttribute("userid", userId);
	model.addAttribute("note", userService.findNote(userId, noteId));
	return "editnote";
    }

    @Override
    @RequestMapping(path = "/notes/delete/{userid}/{noteid}", method = RequestMethod.GET)
    public String deleteNote(@PathVariable(name = "userid") String userId,
	    @PathVariable(name = "noteid") String noteId) {
	userService.deleteNote(userId, noteId);
	return "redirect:/notes/" + userId;
    }

    @Override
    @RequestMapping(path = "notes/{userid}", method = RequestMethod.POST)
    public String saveNote(@PathVariable(name = "userid") String userId, Note note) {
	userService.insertOrUpdateNote(userId, note);
	return "redirect:/notes/" + userId;
    }
}
