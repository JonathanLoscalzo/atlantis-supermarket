package com.atlantis.supermarket.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atlantis.supermarket.business.product.ProviderService;
import com.atlantis.supermarket.core.product.Provider;
import com.atlantis.supermarket.infrastructure.product.ProviderRepository;
import com.github.javafaker.Faker;

@Component
public class SeedProviders {

    @Autowired
    ProviderRepository repo;
    
    public void set() {
	List<Provider> providers = new ArrayList<>();
	if (repo.findAll().size() == 0) {
	    for (int i = 0; i < 10; i++) {
		Faker faker = new Faker();
		Provider provider = new Provider();
		provider.setEmail("loscalzo.jony@gmail.com");
		provider.setName(faker.name().fullName());
		provider.setPhone(faker.phoneNumber().cellPhone());
		providers.add(provider);
	    }
	   repo.saveAll(providers);
	}
    }
}
