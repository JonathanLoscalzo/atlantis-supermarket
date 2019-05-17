package com.atlantis.supermarket.business.product;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.atlantis.supermarket.business.ServiceImpl;
import com.atlantis.supermarket.core.product.Category;
import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.exception.ProductNotFoundException;
import com.atlantis.supermarket.core.shared.EntityNotFoundException;
import com.atlantis.supermarket.infrastructure.product.CategoryRepository;
import com.atlantis.supermarket.infrastructure.product.ProductRepository;

@Service
public class ProductService extends ServiceImpl<Product> {

    private CategoryRepository catRepo;

    public ProductService(ProductRepository repo, CategoryRepository catRepo) {
	super(repo);
	this.catRepo = catRepo;
    }

    /**
     * use CreateProductUseCase
     */
    @Override
    public Product save(Product entity) {
	return null;
    }

    public Product addCategory(UUID category, UUID product) {
	Product p = this.repo.findById(product).orElseThrow(() -> new ProductNotFoundException(product));
	Category c = this.catRepo.findById(category)
		.orElseThrow(() -> new EntityNotFoundException("nose", "Category", category.toString()));

	p.addCategory(c);
	this.repo.save(p);
	return p;

    }
}
