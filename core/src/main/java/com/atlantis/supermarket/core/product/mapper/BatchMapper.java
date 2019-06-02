package com.atlantis.supermarket.core.product.mapper;

import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.dto.BatchDto;
import com.atlantis.supermarket.core.product.dto.ProductDto;


public interface BatchMapper {

    public BatchDto toDto(Batch p) ;
}
