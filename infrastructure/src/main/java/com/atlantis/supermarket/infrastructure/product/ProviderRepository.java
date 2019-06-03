package com.atlantis.supermarket.infrastructure.product;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.atlantis.supermarket.core.product.Provider;

public interface ProviderRepository extends JpaRepository<Provider, UUID> {
    
}
	