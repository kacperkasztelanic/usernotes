package com.kasztelanic.usernotes.controller;

import com.kasztelanic.usernotes.persistence.entity.Note;

import org.springframework.ui.Model;

public interface NotesController {

    String findNotesForUser(Model model, String id);

    String createNote(Model model, String userid);

    String updateNote(Model model, String userid, String noteid);

    String deleteNote(String userid, String noteid);

    String saveNote(String userid, Note note);
}
