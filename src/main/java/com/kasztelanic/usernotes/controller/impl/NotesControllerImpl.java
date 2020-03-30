package com.kasztelanic.usernotes.controller.impl;

import com.kasztelanic.usernotes.controller.NotesController;
import com.kasztelanic.usernotes.persistence.entity.Note;
import com.kasztelanic.usernotes.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotesControllerImpl implements NotesController {

    private final UserService userService;

    @Autowired
    public NotesControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("/notes/{userid}")
    public String findNotesForUser(Model model, @PathVariable(value = "userid") String userId) {
        model.addAttribute("usernotes", userService.findOne(userId).getNotes());
        model.addAttribute("user", userService.findOne(userId));
        return "usernotes";
    }

    @Override
    @GetMapping("/notes/add/{userid}")
    public String createNote(Model model, @PathVariable(name = "userid") String userId) {
        model.addAttribute("note", new Note());
        model.addAttribute("userid", userId);
        return "editnote";
    }

    @Override
    @GetMapping("/notes/edit/{userid}/{noteid}")
    public String updateNote(Model model, @PathVariable(value = "userid") String userId,
            @PathVariable(value = "noteid") String noteId) {
        model.addAttribute("userid", userId);
        model.addAttribute("note", userService.findNote(userId, noteId));
        return "editnote";
    }

    @Override
    @GetMapping("/notes/delete/{userid}/{noteid}")
    public String deleteNote(@PathVariable(name = "userid") String userId,
            @PathVariable(name = "noteid") String noteId) {
        userService.deleteNote(userId, noteId);
        return "redirect:/notes/" + userId;
    }

    @Override
    @PostMapping("notes/{userid}")
    public String saveNote(@PathVariable(name = "userid") String userId, Note note) {
        userService.insertOrUpdateNote(userId, note);
        return "redirect:/notes/" + userId;
    }
}
