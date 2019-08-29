package com.atlantis.supermarket.infrastructure.product;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.product.Provider;
import com.atlantis.supermarket.core.product.dto.ProviderDto;

import com.atlantis.supermarket.core.product.mapper.ProviderMapper;

@Component
public class ProviderMapperImpl implements ProviderMapper {

    @Override
    public ProviderDto toDto(Provider p) {
	if (p == null)
	    return null;
	ProviderDto dto = new ProviderDto();

	dto.setEmail(p.getEmail());
	dto.setName(p.getName());
	dto.setPhone(p.getPhone());
	dto.setId(p.getId().toString());

	return dto;
    }

    @Override
    public Provider toEntity(ProviderDto provider) {
	Provider entity = new Provider();
	entity.setEmail(provider.getEmail());
	entity.setName(provider.getName());
	entity.setPhone(provider.getPhone());
	
	if (provider.getId() != null) {
	    entity.setId(UUID.fromString(provider.getId().toString()));
	}
	
	return entity;
    }

}
