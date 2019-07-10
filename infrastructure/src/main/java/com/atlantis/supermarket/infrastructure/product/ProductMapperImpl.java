package com.atlantis.supermarket.infrastructure.product;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.dto.CategoryDto;
import com.atlantis.supermarket.core.product.dto.ProductDto;
import com.atlantis.supermarket.core.product.mapper.BatchMapper;
import com.atlantis.supermarket.core.product.mapper.CategoryMapper;
import com.atlantis.supermarket.core.product.mapper.ProductMapper;
import com.atlantis.supermarket.core.product.mapper.ProviderMapper;
import com.atlantis.supermarket.core.product.search.ProductSolrDto;
import com.atlantis.supermarket.core.shared.search.SolrId;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    BatchMapper batchMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ProviderMapper providerMapper;

    /**
     * only for client role
     */
    @Override
    public ProductDto toDto(ProductSolrDto x) {
	ProductDto dto = new ProductDto();
	dto.setDescription(x.getDescription());
	dto.setName(x.getName());
	dto.setBrand(x.getBrand());
	dto.setId(new SolrId(x.getId()).getId().toString());
	dto.setRetailPrice(x.getRetailPrice());
	Collection<CategoryDto> categories = x.getCategories()
		.stream()
		.map(c -> new CategoryDto(c))
		.collect(Collectors.toList());
	dto.setCategories(categories);
	return dto;
    }

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
    public ProductDto toDtoAllBatches(Product p) {
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

	dto.setBatches(p.getBatches()
		.stream()
		.map(batchMapper::toDto)
		.collect(Collectors.toList()));

	dto.setCategories(p.getCategories()
		.stream()
		.map(categoryMapper::toDto)
		.collect(Collectors.toList()));
	return dto;
    }

}
