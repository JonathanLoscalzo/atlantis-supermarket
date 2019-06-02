package com.atlantis.supermarket.core.product.mapper;

import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.core.product.Category;
import com.atlantis.supermarket.core.product.dto.BatchDto;
import com.atlantis.supermarket.core.product.dto.CategoryDto;

public interface CategoryMapper {
    public CategoryDto toDto(Category p) ;
}
