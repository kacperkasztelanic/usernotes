package com.kasztelanic.usernotes.config;

import com.kasztelanic.usernotes.persistence.repository.common.UserRepository;
import com.kasztelanic.usernotes.persistence.repository.db.UserDbRepository;
import com.kasztelanic.usernotes.persistence.repository.file.UserFileRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class GlobalConfig {

    @Value("${repository_type}")
    private String repositoryType;

    @Bean
    public UserRepository userRepository(UserDbRepository userDbRepository, UserFileRepository userFileRepository) {
        if (repositoryType.equalsIgnoreCase(RepositoryType.DB.name())) {
            return userDbRepository;
        }
        if (repositoryType.equalsIgnoreCase(RepositoryType.FILE.name())) {
            return userFileRepository;
        }
        throw new IllegalArgumentException(//
                String.format("Only repositories defined in %s:%s are allowed",//
                        RepositoryType.class.getCanonicalName(), Arrays.toString(RepositoryType.values())//
                )//
        );
    }
}
