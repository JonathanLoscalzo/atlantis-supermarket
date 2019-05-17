package com.atlantis.supermarket.business.product;

import org.springframework.stereotype.Service;

import com.atlantis.supermarket.business.ServiceImpl;
import com.atlantis.supermarket.core.product.Provider;
import com.atlantis.supermarket.infrastructure.product.ProviderRepository;

@Service
public class ProviderService extends ServiceImpl<Provider>{
    
    public ProviderService(ProviderRepository providers) {
	super(providers);
    }

}
