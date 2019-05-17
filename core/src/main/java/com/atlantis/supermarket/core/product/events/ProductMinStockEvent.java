package com.atlantis.supermarket.core.product.events;

import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.shared.BaseEvent;

public class ProductMinStockEvent extends BaseEvent {

    private Product product;
    
    public ProductMinStockEvent(Product product) {
	this.setProduct(product);
    }

    public Product getProduct() {
	return product;
    }

    public void setProduct(Product product) {
	this.product = product;
    }
}
