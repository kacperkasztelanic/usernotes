package com.kasztelanic.usernotes.persistence.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@EqualsAndHashCode(of = { "uuid" })
@ToString
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    @Getter
    @Setter
    private String uuid;

    @Column(nullable = false, length = 30)
    @Getter
    @Setter
    private String title;

    @Column(nullable = false, length = 255)
    @Getter
    @Setter
    private String text;

    public Note() {
    }

    public Note(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
