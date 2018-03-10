package com.kasztelanic.usernotes.service.serviceimpl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kasztelanic.usernotes.persistence.entity.Note;
import com.kasztelanic.usernotes.persistence.entity.User;
import com.kasztelanic.usernotes.persistence.repository.common.UserRepository;
import com.kasztelanic.usernotes.service.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource(name = "userRepository")
	private UserRepository userRepository;

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findOne(String id) {
		return userRepository.findOne(id);
	}

	@Override
	public void delete(String id) {
		userRepository.delete(id);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public Note findNote(String userId, String noteId) {
		User user = findOne(userId);
		Set<Note> notes = user.getNotes();
		return notes.stream().filter(n -> n.getUuid().equals(noteId)).findFirst().get();
	}

	@Override
	public void insertOrUpdateNote(String userId, Note note) {
		User user = findOne(userId);
		Set<Note> notes = user.getNotes();
		if (notes.contains(note)) {
			notes.remove(note);
		}
		notes.add(note);
		save(user);
	}

	@Override
	public void deleteNote(String userId, String noteId) {
		User user = findOne(userId);
		user.getNotes().removeIf(n -> n.getUuid().equals(noteId));
		save(user);
	}
}
