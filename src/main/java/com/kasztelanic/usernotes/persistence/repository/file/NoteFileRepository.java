package com.kasztelanic.usernotes.persistence.repository.file;

import com.kasztelanic.usernotes.persistence.entity.Note;
import com.kasztelanic.usernotes.persistence.repository.common.NoteRepository;

public class NoteFileRepository implements NoteRepository {
    @Override
    public <S extends Note> S save(S entity) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public <S extends Note> Iterable<S> save(Iterable<S> entities) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Note findOne(Long id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean exists(Long id) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public Iterable<Note> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Iterable<Note> findAll(Iterable<Long> ids) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public long count() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public void delete(Long id) {
	// TODO Auto-generated method stub

    }

    @Override
    public void delete(Note entity) {
	// TODO Auto-generated method stub

    }

    @Override
    public void delete(Iterable<? extends Note> entities) {
	// TODO Auto-generated method stub

    }

    @Override
    public void deleteAll() {
	// TODO Auto-generated method stub

    }
}
