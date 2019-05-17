package com.atlantis.supermarket.business.product.useCases.createProduct;

import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.shared.business.OutputPort;

public class CreateProductOutput extends OutputPort {

    private Product product;

    public CreateProductOutput(Product p) {
	this.setProduct(p);
    }

    public Product getProduct() {
	return product;
    }

    public void setProduct(Product product) {
	this.product = product;
    }

}
