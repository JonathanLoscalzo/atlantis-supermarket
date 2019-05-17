package com.atlantis.supermarket.business.product;

import org.springframework.stereotype.Service;

import com.atlantis.supermarket.business.ServiceImpl;
import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.infrastructure.product.ProductRepository;

@Service
public class ProductService extends ServiceImpl<Product> {

    public ProductService(ProductRepository repo) {
	super(repo);
    }
    
    /**
     * use CreateProductUseCase
     */
    @Override
    public Product save(Product entity) {
	return null;
    }

}
