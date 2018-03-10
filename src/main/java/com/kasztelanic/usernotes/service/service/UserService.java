package com.kasztelanic.usernotes.service.service;

import com.kasztelanic.usernotes.persistence.entity.User;

public interface UserService {

	public Iterable<User> findAll();

	public User findOne(String id);

	public void delete(String id);

	public User save(User user);
}
