package com.atlantis.supermarket.business.client.useCases.input;

import com.atlantis.supermarket.core.shared.business.InputPort;

public class CreateClientAndUser extends InputPort {
    
    private String name = "";

    private String surname = "";

    private Integer document = 0;
    
    // required
    private String username;

    // required
    private String password;
    
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getDocument() {
        return document;
    }

    public void setDocument(Integer document) {
        this.document = document;
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

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }
    
}
