package com.atlantis.supermarket.core.client.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.atlantis.supermarket.core.sale.Sale;
import com.atlantis.supermarket.core.shared.BaseEntityDto;
import com.atlantis.supermarket.core.user.User;

public class ClientDto extends BaseEntityDto {
    
    private String name;
    
    private String surname;
    
    private Integer document;
   
    private String username;
    
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
