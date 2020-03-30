package com.kasztelanic.usernotes.persistence.entity;

import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@EqualsAndHashCode(of = { "id" })
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    @Getter
    @Setter
    private String id;

    @Column(nullable = false, length = 254)
    @Getter
    @Setter
    private String email;

    @Column(length = 30)
    @Getter
    @Setter
    private String firstname;

    @Column(length = 30)
    @Getter
    @Setter
    private String lastname;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Getter
    private List<Note> notes = new ArrayList<>();

    public User() {
    }

    public User(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public void setNotes(List<Note> notes) {
        this.notes.clear();
        this.notes.addAll(notes);
    }
}
