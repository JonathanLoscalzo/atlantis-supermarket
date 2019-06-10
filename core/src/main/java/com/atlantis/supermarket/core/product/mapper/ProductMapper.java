package com.atlantis.supermarket.core.product.mapper;

import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.dto.ProductDto;

public interface ProductMapper {
    public ProductDto toDto(Product p);
    public ProductDto toDtoAllBatches(Product p);
}
