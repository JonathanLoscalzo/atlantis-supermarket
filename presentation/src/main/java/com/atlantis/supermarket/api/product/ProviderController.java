package com.atlantis.supermarket.api.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atlantis.supermarket.business.product.ProviderService;
import com.atlantis.supermarket.core.product.Provider;
import com.atlantis.supermarket.infrastructure.product.ProviderRepository;
/**
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#core.web
 * @author jloscalzo
 *
 */
@RestController
@RequestMapping("/api/provider")
public class ProviderController {

    @Autowired private ProviderService providerService;
    @Autowired private ProviderRepository providerRepo;
    
    @GetMapping
    public Page<Provider> get(Pageable pageable) {
	return providerRepo.findAll(pageable);
    }
    
    @PostMapping
    public Provider create(@RequestBody Provider provider) {
	return providerService.save(provider);
    }
    
    @PutMapping
    public Provider update(@RequestBody Provider provider) {
	return providerService.save(provider);
    }
    
    @DeleteMapping
    public void update(@RequestBody String identifier) {
	providerService.delete(identifier);
    }
}
