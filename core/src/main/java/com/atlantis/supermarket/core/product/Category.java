package com.atlantis.supermarket.core.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.atlantis.supermarket.core.shared.BaseEntity;

@Entity
@Table(name = "category")
@Access(AccessType.FIELD)
@Where(clause = "deleted = 0")
public class Category extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(nullable=false)
    private String description;
    
    @ManyToMany(mappedBy = "categories")
    private List<Product> products = new ArrayList<>();
    
    public String getDescription() {
	return description;
    }
    public void setDescription(String description) {
	this.description = description;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
}
