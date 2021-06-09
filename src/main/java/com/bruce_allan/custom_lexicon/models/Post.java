package com.bruce_allan.custom_lexicon.models;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", length = 225, nullable = false)
    private String number;

    @Column(name = "definition", columnDefinition = "TEXT", length = 3000, nullable = false)
    private String definition;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User owner;

    public Post() {
    }

    public Post(String number, String definition, User owner) {
        this.number = number;
        this.definition = definition;
        this.owner = owner;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}