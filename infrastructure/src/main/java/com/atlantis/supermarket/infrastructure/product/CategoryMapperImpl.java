package com.atlantis.supermarket.infrastructure.product;

import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.product.Category;
import com.atlantis.supermarket.core.product.dto.CategoryDto;
import com.atlantis.supermarket.core.product.mapper.CategoryMapper;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDto toDto(Category p) {
	CategoryDto dto = new CategoryDto();
	dto.setId(p.getId().toString());
	dto.setDescription(p.getDescription());
	return dto;
    }

}
