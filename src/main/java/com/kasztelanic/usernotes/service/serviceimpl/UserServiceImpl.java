package com.kasztelanic.usernotes.service.serviceimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
		// User existing = userRepository.findByEmail(user.getEmail());
		// if (existing != null) {
		// // existing.setFirstname(user.getFirstname());
		// // existing.setLastname(user.getLastname());
		// // Set<Note> notes = new HashSet<>();
		// // notes.addAll(existing.getNotes());
		// // existing.setNotes(notes);
		// // return userRepository.save(existing);
		// return existing;
		// }
		return userRepository.save(user);
	}
}
