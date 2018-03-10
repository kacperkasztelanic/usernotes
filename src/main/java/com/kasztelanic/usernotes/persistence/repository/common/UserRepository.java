package com.kasztelanic.usernotes.persistence.repository.common;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.kasztelanic.usernotes.persistence.entity.User;

@NoRepositoryBean
public interface UserRepository extends CrudRepository<User, String> {
}
