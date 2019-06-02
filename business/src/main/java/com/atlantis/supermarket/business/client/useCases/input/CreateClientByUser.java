package com.atlantis.supermarket.business.client.useCases.input;

import java.util.UUID;

import com.atlantis.supermarket.core.shared.business.InputPort;

public class CreateClientByUser extends InputPort {
    
    private String name;

    private String surname;

    private Integer document;
    
    private String email;
    
    public UUID userID;
    
    public String username;

    public String getName() {
        return name;
    }

    public CreateClientByUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public CreateClientByUser setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Integer getDocument() {
        return document;
    }

    public CreateClientByUser setDocument(Integer document) {
        this.document = document;
        return this;
    }

    public UUID getUserID() {
        return userID;
    }

    public CreateClientByUser setUserID(UUID userID) {
        this.userID = userID;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
