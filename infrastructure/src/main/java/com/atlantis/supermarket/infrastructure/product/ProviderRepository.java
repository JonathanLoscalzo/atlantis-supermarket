package com.atlantis.supermarket.infrastructure.product;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlantis.supermarket.core.product.Provider;

public interface ProviderRepository extends JpaRepository<Provider, UUID>{

}
