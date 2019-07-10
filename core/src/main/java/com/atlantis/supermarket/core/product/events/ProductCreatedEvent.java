package com.atlantis.supermarket.core.product.events;

import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.shared.BaseEvent;

public class ProductCreatedEvent extends BaseEvent {
    private Product product;
    
    public ProductCreatedEvent(Product product) {
	this.product = product;
    }
    
    public Product getProduct() {
	return product;
    }
}
