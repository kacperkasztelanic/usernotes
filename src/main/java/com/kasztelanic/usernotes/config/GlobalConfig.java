package com.kasztelanic.usernotes.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kasztelanic.usernotes.persistence.repository.common.UserRepository;
import com.kasztelanic.usernotes.persistence.repository.db.UserDbRepository;
import com.kasztelanic.usernotes.persistence.repository.file.UserFileRepository;

@Configuration
public class GlobalConfig {

    @Value("${repository_type}")
    private String repositoryType;

    @Autowired
    private UserDbRepository userDbRepository;
    @Autowired
    private UserFileRepository userFileRepository;

    @Bean
    public UserRepository userRepository() {
        if (repositoryType.equalsIgnoreCase(RepositoryType.DB.name())) {
            return userDbRepository;
        }
        if (repositoryType.equalsIgnoreCase(RepositoryType.FILE.name())) {
            return userFileRepository;
        }
        throw new IllegalArgumentException("Only repositories defined in " + RepositoryType.class.getCanonicalName()
                + ":" + Arrays.toString(RepositoryType.values()) + " are allowed!");
    }
}
