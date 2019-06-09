package com.atlantis.supermarket.infrastructure.product;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.dto.ProductDto;
import com.atlantis.supermarket.core.product.mapper.BatchMapper;
import com.atlantis.supermarket.core.product.mapper.CategoryMapper;
import com.atlantis.supermarket.core.product.mapper.ProductMapper;
import com.atlantis.supermarket.core.product.mapper.ProviderMapper;

@Component
public class ProductMapperImpl implements ProductMapper {
    
    @Autowired BatchMapper batchMapper;
    @Autowired CategoryMapper categoryMapper;
    @Autowired ProviderMapper providerMapper;
    
    @Override
    public ProductDto toDto(Product p) {
	ProductDto dto = new ProductDto();
	dto.setSku(p.getSku());
	dto.setId(p.getId().toString());
	dto.setUpc(p.getUpc());
	dto.setName(p.getName());
	dto.setBrand(p.getBrand());
	dto.setMinStock(p.getMinStock());
	dto.setProviderPrice(p.getProviderPrice());
	dto.setRetailPrice(p.getRetailPrice());
	dto.setDescription(p.getDescription());
	dto.setType(p.getType());
	dto.setBatches(p.getConsumableBatches()
		.stream()
		.map(batchMapper::toDto)
		.collect(Collectors.toList()));
	dto.setProvider(providerMapper.toDto(p.getProvider()));
	dto.setCategories(p.getCategories()
		.stream()
		.map(categoryMapper::toDto)
		.collect(Collectors.toList()));
	return dto;
    }

    @Override
    public ProductDto toDtoSimple(Product p) {
	ProductDto dto = new ProductDto();
	
	dto.setId(p.getId().toString());
	dto.setUpc(p.getUpc());
	dto.setName(p.getName());
	dto.setBrand(p.getBrand());
	dto.setMinStock(p.getMinStock());
	dto.setProviderPrice(p.getProviderPrice());
	dto.setRetailPrice(p.getRetailPrice());
	dto.setDescription(p.getDescription());
	dto.setType(p.getType());
	dto.setProvider(providerMapper.toDto(p.getProvider()));
	dto.setCategories(p.getCategories()
		.stream()
		.map(categoryMapper::toDto)
		.collect(Collectors.toList()));
	return dto;
    }

}
