package com.kasztelanic.usernotes.persistence.repository.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kasztelanic.usernotes.persistence.entity.User;
import com.kasztelanic.usernotes.persistence.repository.common.UserRepository;

@Repository
public class UserDbRepository implements UserRepository {

    @Autowired
    private UserCrudRepository userCrudRepository;

    @Override
    public <S extends User> S save(S entity) {
	return userCrudRepository.save(entity);
    }

    @Override
    public <S extends User> Iterable<S> save(Iterable<S> entities) {
	return userCrudRepository.save(entities);
    }

    @Override
    public User findOne(String id) {
	return userCrudRepository.findOne(id);
    }

    @Override
    public boolean exists(String id) {
	return userCrudRepository.exists(id);
    }

    @Override
    public Iterable<User> findAll() {
	return userCrudRepository.findAll();
    }

    @Override
    public Iterable<User> findAll(Iterable<String> ids) {
	return userCrudRepository.findAll(ids);
    }

    @Override
    public long count() {
	return userCrudRepository.count();
    }

    @Override
    public void delete(String id) {
	userCrudRepository.delete(id);
    }

    @Override
    public void delete(User entity) {
	userCrudRepository.delete(entity);
    }

    @Override
    public void delete(Iterable<? extends User> entities) {
	userCrudRepository.delete(entities);
    }

    @Override
    public void deleteAll() {
	userCrudRepository.deleteAll();
    }
}

@Repository
interface UserCrudRepository extends JpaRepository<User, String> {
}
