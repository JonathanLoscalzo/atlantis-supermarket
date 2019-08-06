package com.atlantis.supermarket.core.product.exception;

import java.util.UUID;

public class ProductNotSatisfiedStockException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String requested;
    private String stock;
    private UUID id;
    
    public ProductNotSatisfiedStockException(String requested, String stock, UUID id) {
	this.requested = requested;
	this.stock = stock;
	this.setId(id);
    }

    public String getRequested() {
        return requested;
    }

    public void setRequested(String requested) {
        this.requested = requested;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public UUID getId() {
	return id;
    }

    public void setId(UUID id) {
	this.id = id;
    }

}
