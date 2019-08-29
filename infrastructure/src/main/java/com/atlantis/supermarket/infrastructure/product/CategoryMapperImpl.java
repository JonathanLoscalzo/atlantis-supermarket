package com.atlantis.supermarket.infrastructure.product;

import java.util.UUID;

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

    public Category toEntity(CategoryDto c) {
	Category dto = new Category();
	if (c.getId() != null) {
	    dto.setId(UUID.fromString(c.getId().toString()));
	}
	dto.setDescription(c.getDescription());

	return dto;
    }

}
