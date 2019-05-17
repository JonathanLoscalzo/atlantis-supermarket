package com.atlantis.supermarket.infrastructure.sale;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlantis.supermarket.core.sale.Sale;
import com.atlantis.supermarket.infrastructure.shared.DetachableEntityCustom;

public interface SaleRepository extends JpaRepository<Sale, UUID>, DetachableEntityCustom {

}
