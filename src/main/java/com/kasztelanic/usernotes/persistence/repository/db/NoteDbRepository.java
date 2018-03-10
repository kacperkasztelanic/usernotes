package com.kasztelanic.usernotes.persistence.repository.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kasztelanic.usernotes.persistence.entity.Note;
import com.kasztelanic.usernotes.persistence.repository.common.NoteRepository;

@Repository
public class NoteDbRepository implements NoteRepository {

	@Autowired
	private NoteCrudRepository noteCrudRepository;

	@Override
	public <S extends Note> S save(S entity) {
		return noteCrudRepository.save(entity);
	}

	@Override
	public <S extends Note> Iterable<S> save(Iterable<S> entities) {
		return noteCrudRepository.save(entities);
	}

	@Override
	public Note findOne(Long id) {
		return noteCrudRepository.findOne(id);
	}

	@Override
	public boolean exists(Long id) {
		return noteCrudRepository.exists(id);
	}

	@Override
	public Iterable<Note> findAll() {
		return noteCrudRepository.findAll();
	}

	@Override
	public Iterable<Note> findAll(Iterable<Long> ids) {
		return noteCrudRepository.findAll(ids);
	}

	@Override
	public long count() {
		return noteCrudRepository.count();
	}

	@Override
	public void delete(Long id) {
		noteCrudRepository.delete(id);
	}

	@Override
	public void delete(Note entity) {
		noteCrudRepository.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends Note> entities) {
		noteCrudRepository.delete(entities);
	}

	@Override
	public void deleteAll() {
		noteCrudRepository.deleteAll();
	}
}

@Repository
interface NoteCrudRepository extends CrudRepository<Note, Long> {
}
