package com.atlantis.supermarket.infrastructure.product;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.infrastructure.shared.DetachableEntityCustom;

public interface ProductRepository extends JpaRepository<Product, UUID>, DetachableEntityCustom {

}
