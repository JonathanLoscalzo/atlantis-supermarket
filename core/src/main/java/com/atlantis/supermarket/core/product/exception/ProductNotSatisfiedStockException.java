package com.atlantis.supermarket.core.product.exception;

public class ProductNotSatisfiedStockException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String requested;
    private String stock;
    
    public ProductNotSatisfiedStockException(String requested, String stock) {
	this.requested = requested;
	this.stock = stock;
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

}
