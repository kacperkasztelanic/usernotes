package com.kasztelanic.usernotes.persistence.repository.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kasztelanic.usernotes.persistence.entity.Note;
import com.kasztelanic.usernotes.persistence.entity.User;
import com.kasztelanic.usernotes.persistence.repository.common.UserRepository;

@Repository
public class UserFileRepository implements UserRepository {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${file_repository}")
    private String databasePath;

    @Override
    public <S extends User> S save(S entity) {
        Map<String, User> userMap = loadData();
        if (entity.getId() == null || entity.getId().isEmpty()) {
            String id = UUID.randomUUID().toString();
            entity.setId(id);
            userMap.put(id, entity);
        } else {
            userMap.put(entity.getId(), entity);
        }
        List<Note> notes = entity.getNotes();
        for (Note note : notes) {
            if (note.getUuid() == null || note.getUuid().isEmpty()) {
                note.setUuid(UUID.randomUUID().toString());
            }
        }
        saveData(userMap);
        return entity;
    }

    @Override
    public <S extends User> Iterable<S> save(Iterable<S> entities) {
        return StreamSupport.stream(entities.spliterator(), false).map(this::save).collect(Collectors.toList());
    }

    @Override
    public User findOne(String id) {
        return loadData().entrySet().stream().filter(x -> x.getKey().equals(id)).map(Map.Entry::getValue).findFirst()
                .orElse(null);
    }

    @Override
    public boolean exists(String id) {
        return Objects.nonNull(findOne(id));
    }

    @Override
    public Iterable<User> findAll() {
        return loadData().values();
    }

    @Override
    public Iterable<User> findAll(Iterable<String> ids) {
        Set<String> idSet = StreamSupport.stream(ids.spliterator(), false).collect(Collectors.toSet());
        return loadData().entrySet().stream().filter(x -> idSet.contains(x.getKey())).map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return loadData().size();
    }

    @Override
    public void delete(String id) {
        Map<String, User> userMap = loadData();
        userMap.remove(id);
        saveData(userMap);
    }

    @Override
    public void delete(User entity) {
        delete(entity.getId());
    }

    @Override
    public void delete(Iterable<? extends User> entities) {
        Map<String, User> userMap = loadData();
        for (User entity : entities) {
            userMap.remove(entity.getId());
        }
        saveData(userMap);
    }

    @Override
    public void deleteAll() {
        saveData(Collections.emptyMap());
    }

    private Map<String, User> loadData() {
        try {
            List<User> userList = objectMapper.readValue(new File(databasePath), new TypeReference<List<User>>() {
            });
            return userList.stream().collect(Collectors.toMap(User::getId, u -> u));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    private void saveData(Map<String, User> data) {
        try {
            objectMapper.writeValue(new FileWriter(new File(databasePath)), data.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
