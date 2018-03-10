package com.kasztelanic.usernotes.boot;

import java.util.HashSet;
import java.util.Set;

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
		System.out.println("Deleted");
		Note note = new Note();
		note.setText("First note");
		note.setTitle("Title");
		Set<Note> notes = new HashSet<>();
		notes.add(note);
		User user = new User("Fname", "Lname", "f.l@gmail.com");
		user.setNotes(notes);
		userRepository.save(user);

		System.out.println("Saved");
	}
}
