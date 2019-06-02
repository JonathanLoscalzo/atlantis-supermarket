package com.atlantis.supermarket.infrastructure.product;

import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.dto.ProductDto;
import com.atlantis.supermarket.core.product.mapper.ProductMapper;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toDto(Product p) {
	// TODO Auto-generated method stub
	return null;
    }

}
