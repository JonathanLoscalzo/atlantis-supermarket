package com.atlantis.supermarket.infrastructure.product;

import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.product.Provider;
import com.atlantis.supermarket.core.product.dto.ProviderDto;

import com.atlantis.supermarket.core.product.mapper.ProviderMapper;

@Component
public class ProviderMapperImpl implements ProviderMapper {

    @Override
    public ProviderDto toDto(Provider p) {
	ProviderDto dto = new ProviderDto();
	
	dto.setEmail(p.getEmail());
	dto.setName(p.getName());
	dto.setPhone(p.getPhone());
	dto.setId(p.getId().toString());
	
	return dto;
    }

}
