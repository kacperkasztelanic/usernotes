package com.kasztelanic.usernotes.persistence.repository.db;

import com.kasztelanic.usernotes.persistence.entity.User;
import com.kasztelanic.usernotes.persistence.repository.common.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDbRepository extends JpaRepository<User, String>, UserRepository {
}
