package com.bruce_allan.custom_lexicon.models;


import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;


    @Column(name = "password")
    private String password;


    public User(){

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    
    }

    public User(User copy){
        id = copy.id;
        username = copy.username;
        password = copy.password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

    
