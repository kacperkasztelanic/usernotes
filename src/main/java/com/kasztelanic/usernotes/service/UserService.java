package com.kasztelanic.usernotes.service;

import com.kasztelanic.usernotes.persistence.entity.Note;
import com.kasztelanic.usernotes.persistence.entity.User;

public interface UserService {

    Iterable<User> findAll();

    User findOne(String id);

    void delete(String id);

    User save(User user);

    Note findNote(String userId, String noteId);

    void insertOrUpdateNote(String userId, Note note);

    void deleteNote(String userId, String noteId);
}
