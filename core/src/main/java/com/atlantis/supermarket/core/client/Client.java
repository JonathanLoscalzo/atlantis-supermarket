package com.atlantis.supermarket.core.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.atlantis.supermarket.core.shared.BaseEntity;
import com.atlantis.supermarket.core.user.User;

@Entity
@Table(name = "client")
@Access(AccessType.FIELD)
public class Client extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "NOMBRE", nullable = false)
    private String name;

    @Column(name = "APELLIDO", nullable = false)
    private String surname;

    @Column(name = "DNI", nullable = false)
    private Integer document;

    @Column(name = "DELETED", nullable = false)
    private Boolean deleted;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    /*
     * @OneToMany private Collection<Venta> ventas;
     */

    public Client(){}

    public static Client createClientFromUser(User user, String name, String surname, Integer document) {
	Client client = new Client();
	client.setName(name);
	client.setSurname(surname);
	client.setUser(user);
	client.setDocument(document);
	
	return client;
    }

    public void delete() {
	deleted = true;
    }

    public Boolean getDeleted() {
	return deleted;
    }
    
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
