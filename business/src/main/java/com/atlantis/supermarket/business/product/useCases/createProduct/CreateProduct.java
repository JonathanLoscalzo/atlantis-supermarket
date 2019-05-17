package com.atlantis.supermarket.business.product.useCases.createProduct;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.core.product.Product;

import org.springframework.stereotype.Service;

import com.atlantis.supermarket.core.product.factory.ProductFactory;
import com.atlantis.supermarket.core.shared.business.UseCaseOutput;
import com.atlantis.supermarket.infrastructure.product.ProductRepository;

@Service
public class CreateProduct implements UseCaseOutput<CreateProductInput, CreateProductOutput> {

    @Autowired
    private ProductFactory factory;

    @Autowired
    private ProductRepository products;

    @Override
    @Transactional
    public CreateProductOutput handle(CreateProductInput input) {
	Product p = null;

	switch (input.getType()) {
	case DEFAULT:
	    p = factory.createDefaultProduct(input.getBrand(), input.getName(), input.getUpc(), input.getMinStock(),
		    input.getRetailPrice(), input.getProviderPrice(), input.getStock(), input.getBatchDetails(),
		    input.getDescription());
	    break;
	case EXPIRATION:
	    p = factory.createPerishableProduct(input.getBrand(), input.getName(), input.getUpc(), input.getMinStock(),
		    input.getRetailPrice(), input.getProviderPrice(), input.getStock(), input.getBatchDetails(),
		    input.getDescription(), input.getExpiration());
	    break;
	default:
	    // TODO: Throw some exception
	    break;
	}

	products.save(p);

	return new CreateProductOutput(p);
    }

}
