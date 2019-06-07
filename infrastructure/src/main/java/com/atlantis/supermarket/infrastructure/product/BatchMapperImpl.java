package com.atlantis.supermarket.infrastructure.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.core.product.dto.BatchDto;
import com.atlantis.supermarket.core.product.mapper.BatchMapper;
import com.atlantis.supermarket.core.product.mapper.ProductMapper;

@Component
public class BatchMapperImpl implements BatchMapper {

    @Autowired ProductMapper productMapper;
    
    @Override
    public BatchDto toDto(Batch b) {
	BatchDto dto = new BatchDto();
	
	dto.setId(b.getId().toString());
	dto.setExpiration(b.getExpiration());
	dto.setEntry(b.getEntry());
	dto.setDetail(b.getDetail());
	dto.setRemainingUnits(b.getRemainingUnits());
	//dto.setProduct(b.getProducto());
	
	return dto;
	
    }
    
    @Override
    public BatchDto toDtoWithProduct(Batch b) {
	BatchDto dto = new BatchDto();
	
	dto.setId(b.getId().toString());
	dto.setExpiration(b.getExpiration());
	dto.setEntry(b.getEntry());
	dto.setDetail(b.getDetail());
	dto.setRemainingUnits(b.getRemainingUnits());
	dto.setProduct(productMapper.toDtoSimple(b.getProducto()));
	
	return dto;
	
    }

}
