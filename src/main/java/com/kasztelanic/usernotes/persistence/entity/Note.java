package com.kasztelanic.usernotes.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.kasztelanic.usernotes.utils.UuidGenerator;

@Entity
public class Note {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, updatable = false, unique = true, length = 36)
    private String uuid;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private LocalDateTime lastEditionDate;

    @Column(nullable = false, length = 255)
    private String text;

    public Note() {
	LocalDateTime now = LocalDateTime.now();
	this.creationDate = now;
	this.lastEditionDate = now;
	this.uuid = UuidGenerator.generate();
    }

    public Note(String title, String text) {
	LocalDateTime now = LocalDateTime.now();
	this.title = title;
	this.text = text;
	this.creationDate = now;
	this.lastEditionDate = now;
	this.uuid = UuidGenerator.generate();
    }

    public Note(String title, String text, LocalDateTime creationDate, LocalDateTime lastEditionDate) {
	this.title = title;
	this.text = text;
	this.creationDate = creationDate;
	this.lastEditionDate = lastEditionDate;
	this.uuid = UuidGenerator.generate();
    }

    public Long getId() {
	return id;
    }

    public String getUuid() {
	return uuid;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public LocalDateTime getCreationDate() {
	return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
	this.creationDate = creationDate;
    }

    public LocalDateTime getLastEditionDate() {
	return lastEditionDate;
    }

    public void setLastEditionDate(LocalDateTime lastEditionDate) {
	this.lastEditionDate = lastEditionDate;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Note other = (Note) obj;
	if (uuid == null) {
	    if (other.uuid != null)
		return false;
	} else if (!uuid.equals(other.uuid))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Note [title=" + title + ", creationDate=" + creationDate + ", lastEditionDate=" + lastEditionDate
		+ ", text=" + text + "]";
    }
}
