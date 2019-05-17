package com.atlantis.supermarket.infrastructure.product;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlantis.supermarket.core.product.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
