package com.atlantis.supermarket.business.product.useCases.createProduct;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlantis.supermarket.business.product.ProviderService;
import com.atlantis.supermarket.core.product.Category;
import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.Provider;
import com.atlantis.supermarket.core.product.factory.ProductFactory;
import com.atlantis.supermarket.core.shared.business.UseCaseOutput;
import com.atlantis.supermarket.infrastructure.product.CategoryRepository;
import com.atlantis.supermarket.infrastructure.product.ProductRepository;

@Service
public class UpdateProduct  implements UseCaseOutput<CreateProductInput, CreateProductOutput> {

    @Autowired
    private ProductFactory factory;

    @Autowired
    private ProductRepository products;

    @Autowired
    private ProviderService providers;
    @Autowired
    private CategoryRepository categories;

    @Override
    @Transactional
    public CreateProductOutput handle(CreateProductInput input) {
	Product product = products.getOne(UUID.fromString(input.getId()));
	
	Provider provider = providers.retrieve(input.getProviderId());
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
	
	Collection<Category> categoriesRes = categories.findAllById(input
		.getCategories()
		.stream()
		.map(UUID::fromString)
		.collect(Collectors.toList()));
	
	p.setSku(input.getSku());
	p.setProvider(provider);
	p.setCategories(categoriesRes);
	p.setId(UUID.fromString(input.getId()));
	p.setBatches(product.getBatches());
	
	products.save(p);

	return new CreateProductOutput(p);
    }
}
