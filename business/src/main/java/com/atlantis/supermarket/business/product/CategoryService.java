package com.atlantis.supermarket.business.product;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.atlantis.supermarket.business.ServiceImpl;
import com.atlantis.supermarket.core.product.Category;
import com.atlantis.supermarket.infrastructure.product.CategoryRepository;

@Service
public class CategoryService extends ServiceImpl<Category>{

    protected CategoryService(CategoryRepository repo) {
	super(repo);
    }

}
