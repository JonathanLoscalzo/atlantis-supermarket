package com.atlantis.supermarket.infrastructure.client;

import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.client.Client;
import com.atlantis.supermarket.core.client.dto.ClientDto;
import com.atlantis.supermarket.core.client.mapper.ClientMapper;

@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientDto toDto(Client p) {
	ClientDto dto = new ClientDto();
	
	dto.setId(p.getId().toString());
	dto.setDocument(p.getDocument());
	dto.setEmail(p.getUser().getEmail());
	dto.setSurname(p.getSurname());
	dto.setUsername(p.getUser().getUsername());
	dto.setName(p.getName());
	
	return dto;
    }
    
}
