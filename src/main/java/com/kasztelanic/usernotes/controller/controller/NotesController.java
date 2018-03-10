package com.kasztelanic.usernotes.controller.controller;

import org.springframework.ui.Model;

import com.kasztelanic.usernotes.persistence.entity.Note;

public interface NotesController {

	String findNotesForUser(Model model, String id);

	String createNote(Model model, String userid);

	String updateNote(Model model, String userid, String noteid);

	String deleteNote(String userid, String noteid);

	String saveNote(String userid, Note note);
}
