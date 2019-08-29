package com.atlantis.supermarket.core.product.mapper;

import com.atlantis.supermarket.core.product.Provider;
import com.atlantis.supermarket.core.product.dto.ProviderDto;

public interface ProviderMapper {
    ProviderDto toDto(Provider p);

    Provider toEntity(ProviderDto provider);
}
