package com.atlantis.supermarket.core.product.exception;

import java.util.UUID;

import com.atlantis.supermarket.core.shared.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {

    private UUID product;

    public ProductNotFoundException(UUID product) {
	super("no encontrada", "Producto", product.toString());
	this.setProduct(product);
    }

    public UUID getProduct() {
	return product;
    }

    public void setProduct(UUID product) {
	this.product = product;
    }

    private static final long serialVersionUID = 1L;

}
