package com.atlantis.supermarket.core.client.mapper;

import com.atlantis.supermarket.core.client.Client;
import com.atlantis.supermarket.core.client.dto.ClientDto;

public interface ClientMapper {
    //ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    public ClientDto toDto(Client p);
}
