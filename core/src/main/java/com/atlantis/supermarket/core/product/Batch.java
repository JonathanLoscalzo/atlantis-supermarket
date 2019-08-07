package com.atlantis.supermarket.core.product;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.atlantis.supermarket.core.product.events.BatchEmptyEvent;
import com.atlantis.supermarket.core.shared.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "batch")
@Access(AccessType.FIELD)
public class Batch extends BaseEntity {

    public enum BatchType {
	DEFAULT, EXPIRATION,
    }

    private static final long serialVersionUID = 1L;

    // location/warehouse
    // in use or warehoused

    @Column(name = "expiration", nullable = true)
    private Date expiration;

    @Column(name = "entry", nullable = false)
    private Date entry;

    @Column(name = "detail", nullable = false)
    private String detail;

    @Column(name = "remainingUnits", nullable = false)
    private Double remainingUnits;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    public Batch() {
    }

    /**
     * consume from this batch
     * 
     * @param quantity
     */
    public void consume(double quantity) {
	if (this.remainingUnits >= quantity) {
	    this.remainingUnits -= quantity;
	}

	if (this.remainingUnits == 0) {
	    this.registerEvent(new BatchEmptyEvent(this));
	}
    }

    public void supply(double quantity) {
	if (quantity < 0) {
	    // exception
	}

	this.remainingUnits += quantity;
    }

    @Override
    public String toString() {
	return this.getClass().getSimpleName() + " [id=" + this.getId() + ", detalle=" + this.getDetail()
		+ ", fecha_entrada=" + this.getEntry() + ", fecha_vencimiento= " + this.getExpiration() + "]";
    }

    public Date getExpiration() {
	return expiration;
    }

    public Batch setExpiration(Date expiration) {
	this.expiration = expiration;
	return this;
    }

    public Date getEntry() {
	return entry;
    }

    public Batch setEntry(Date entry) {
	this.entry = entry;
	return this;
    }

    public String getDetail() {
	return detail;
    }

    public Batch setDetail(String detail) {
	this.detail = detail;
	return this;
    }

    public Double getRemainingUnits() {
	return remainingUnits;
    }

    public Batch setRemainingUnits(Double remainingUnits) {
	this.remainingUnits = remainingUnits;
	return this;
    }

    public Product getProduct() {
	return product;
    }

    public Batch setProduct(Product producto) {
	this.product = producto;
	return this;
    }
    
}
