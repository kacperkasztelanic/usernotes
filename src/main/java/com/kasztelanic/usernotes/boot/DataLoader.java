package com.kasztelanic.usernotes.boot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kasztelanic.usernotes.persistence.entity.Note;
import com.kasztelanic.usernotes.persistence.entity.User;
import com.kasztelanic.usernotes.persistence.repository.common.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        Note note1 = new Note();
        note1.setText("This is the first note...");
        note1.setTitle("Title 1");
        Note note2 = new Note();
        note2.setText("This is the second note...");
        note2.setTitle("Title 2");
        List<Note> notes = new ArrayList<>();
        notes.add(note1);
        notes.add(note2);
        User user1 = new User("John", "Smith", "john.smith@gmail.com");
        user1.setNotes(notes);
        userRepository.save(user1);

        Note note3 = new Note();
        note3.setText("Und hier geht eine andere Aufzeichnung...");
        note3.setTitle("Titel 1.");
        List<Note> notes2 = new ArrayList<>();
        notes2.add(note3);
        User user2 = new User("Wolfgang", "Müller", "wolfgang.mueller@gmail.com");
        user2.setNotes(notes2);
        userRepository.save(user2);
    }
}
