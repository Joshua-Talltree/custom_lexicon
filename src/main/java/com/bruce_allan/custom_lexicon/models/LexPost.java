package com.bruce_allan.custom_lexicon.models;

import javax.persistence.*;

@Entity
@Table(name = "lex posts")
public class LexPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lex_number", length = 225, nullable = false)
    private String lex_number;

    @Column(name = "lex_definition", columnDefinition = "TEXT", length = 3000, nullable = false)
    private String lex_definition;

    @ManyToOne()
    @JoinColumn(name = "owner_id")
    private User owner;

    public LexPost() {
    }

    public LexPost(Long id, String lex_number, String lex_definition, User owner) {
        this.id = id;
        this.lex_number = lex_number;
        this.lex_definition = lex_definition;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLex_number() {
        return lex_number;
    }

    public void setLex_number(String lex_number) {
        this.lex_number = lex_number;
    }

    public String getLex_definition() {
        return lex_definition;
    }

    public void setLex_definition(String lex_definition) {
        this.lex_definition = lex_definition;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}