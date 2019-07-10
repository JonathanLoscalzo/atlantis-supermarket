package com.atlantis.supermarket.core.product.mapper;

import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.dto.ProductDto;
import com.atlantis.supermarket.core.product.search.ProductSolrDto;

public interface ProductMapper {
    public ProductDto toDto(ProductSolrDto x);
    public ProductDto toDto(Product p);
    public ProductDto toDtoAllBatches(Product p);
}
