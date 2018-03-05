package com.kasztelanic.usernotes;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kasztelanic.usernotes.persistence.entity.Note;
import com.kasztelanic.usernotes.persistence.entity.User;
import com.kasztelanic.usernotes.persistence.repository.common.UserRepository;

@SpringBootApplication
public class UsernotesApplication implements CommandLineRunner {

    public static void main(String[] args) {
	SpringApplication.run(UsernotesApplication.class, args);
    }

    @Autowired
    private UserRepository userRepo;

    @Override
    public void run(String... args) throws Exception {
	Note note = new Note();
	userRepo.deleteAll();
	System.out.println("Deleted");
	note.setCreationDate(LocalDateTime.now());
	note.setLastEditionDate(LocalDateTime.now());
	note.setText("First note");
	note.setTitle("Title");
	Set<Note> notes = new HashSet<>();
	notes.add(note);
	User user = new User("Fname", "Lname", "f.l@gmail.com");
	// note.setUser(user);
	user.setNotes(notes);
	userRepo.save(user);
	System.out.println("Saved");
    }
}
