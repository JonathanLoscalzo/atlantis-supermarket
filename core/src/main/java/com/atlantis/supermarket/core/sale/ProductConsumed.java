package com.atlantis.supermarket.core.sale;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.core.shared.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * This entity allows us to know the expiration date with which a product is
 * sold, and to know all the lots where it comes historically.
 * 
 * @author jloscalzo
 *
 */
@Entity
@Table(name = "product_consumed")
@Access(AccessType.FIELD)
public class ProductConsumed extends BaseEntity {
    
    @Transient
    private static final long serialVersionUID = 1L;
    
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.ALL})
    @JoinColumn(name="batch_id")
    @JsonManagedReference
    private Batch batch;
    
    @Column(name = "expiration", nullable = true)
    private Date expiration;
    
    @Column
    private Double quantity;
    
    @ManyToOne
    @JoinColumn(name="item_id")
    @JsonBackReference
    private SaleItem item;

    public Double getQuantity() {
	return quantity;
    }

    public void setQuantity(Double quantity) {
	if (quantity <= 0) {
	    // TODO: throw another exception
	}
	
	this.quantity = quantity;
    }

    public SaleItem getItem() {
        return item;
    }

    public void setItem(SaleItem item) {
        this.item = item;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

}
