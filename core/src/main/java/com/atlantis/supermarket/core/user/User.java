package com.atlantis.supermarket.core.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.atlantis.supermarket.core.shared.BaseEntity;
import com.atlantis.supermarket.core.user.event.UserCreatedEvent;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.atlantis.supermarket.core.client.Client;

@Entity
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public enum UserRole {
	ADMIN, USER, CLIENT
    }

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    
    @Column
    private String email;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> Roles;
    
    @OneToOne()
    @JsonManagedReference
    private Client client;
    

    public User() {
	this.Roles = new HashSet<UserRole>();
	this.registerEvent(new UserCreatedEvent(this.getId()));
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

    public Set<UserRole> getRoles() {
	return Roles;
    }

    public void setRoles(Set<UserRole> roles) {
	Roles = roles;
    }

    public void addRole(UserRole role) {
	this.Roles.add(role);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}