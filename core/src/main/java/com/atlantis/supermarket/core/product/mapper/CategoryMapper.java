package com.atlantis.supermarket.core.product.mapper;

import com.atlantis.supermarket.core.product.Category;
import com.atlantis.supermarket.core.product.dto.CategoryDto;
import com.atlantis.supermarket.core.shared.MapperDto;

public interface CategoryMapper extends MapperDto<Category, CategoryDto> {
    public CategoryDto toDto(Category p) ;
}
