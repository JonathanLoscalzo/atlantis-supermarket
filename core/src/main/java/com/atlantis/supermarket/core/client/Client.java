package com.atlantis.supermarket.core.client;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.atlantis.supermarket.core.client.events.SaleAddedEvent;
import com.atlantis.supermarket.core.sale.Sale;
import com.atlantis.supermarket.core.shared.BaseEntity;
import com.atlantis.supermarket.core.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "client")
@Access(AccessType.FIELD)
public class Client extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "document", nullable = true)
    private Integer document;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    @JsonBackReference
    private Collection<Sale> sales = new ArrayList<Sale>();

    public Client(){}

    public static Client createClientFromUser(User user, String name, String surname, Integer document) {
	Client client = new Client();
	client.setName(name);
	client.setSurname(surname);
	client.setUser(user);
	client.setDocument(document);
	user.setClient(client);
	return client;
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

    public Collection<Sale> getSales() {
        return sales;
    }

    public void setSales(Collection<Sale> sales) {
        this.sales = sales;
    }
    
    public void addSale(Sale sale) {
	this.sales.add(sale);
	this.registerEvent(new SaleAddedEvent());
    }
}
