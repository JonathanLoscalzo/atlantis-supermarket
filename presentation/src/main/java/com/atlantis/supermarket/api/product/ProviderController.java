package com.atlantis.supermarket.api.product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atlantis.supermarket.business.product.ProviderService;
import com.atlantis.supermarket.core.product.Provider;
import com.atlantis.supermarket.core.product.dto.ProviderDto;
import com.atlantis.supermarket.core.product.mapper.ProviderMapper;
import com.atlantis.supermarket.infrastructure.product.ProviderRepository;

/**
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#core.web
 * 
 * @author jloscalzo
 *
 */
@RestController
@RequestMapping("/api/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;
    
    @Autowired
    private ProviderRepository providerRepo; // TODO: DELETE THIS DEPENDENDENCY
    
    @Autowired
    private ProviderMapper mapper;

    @GetMapping
    public Page<ProviderDto> get(Pageable pageable) {
	return providerRepo.findAll(pageable).map(mapper::toDto);
    }

    @GetMapping("/all")
    public List<ProviderDto> get() {
	return providerRepo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{identifier}")
    public Provider get(@PathVariable String identifier) {
	return providerService.retrieve(identifier);
    }

    @PostMapping
    public Provider create(@RequestBody ProviderDto provider) {
	return providerService.save(mapper.toEntity(provider));
    }

    @PutMapping
    public Provider update(@RequestBody ProviderDto provider) {
	return providerService.save(mapper.toEntity(provider));
    }

    @DeleteMapping("/{identifier}")
    public void update(@PathVariable String identifier) {
	providerService.delete(identifier);
    }
}
