package com.atlantis.supermarket.api.shared;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.atlantis.supermarket.core.product.Provider;
import com.atlantis.supermarket.core.product.dto.ProviderDto;
import com.atlantis.supermarket.core.shared.BaseEntity;
import com.atlantis.supermarket.core.shared.BaseService;
import com.atlantis.supermarket.core.shared.MapperDto;

// TODO: implementar esto y probarlo
public abstract class BaseController<T extends BaseEntity, Dto> {
    protected BaseService<T, UUID> service;
    protected JpaRepository<T, UUID> repo;
    protected MapperDto<T,Dto> mapper;
    
    public BaseController(BaseService<T, UUID> service, 
	    JpaRepository<T, UUID> repo,
	    MapperDto<T,Dto> mapper) {
	this.service = service;
	this.repo = repo;
	this.mapper = mapper;
    }

    @GetMapping
    public Page<Dto> get(Pageable pageable) {
	return repo.findAll(pageable).map(mapper::toDto);
    }

    @GetMapping("/all")
    public List<Dto> get() {
	return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{identifier}")
    public Dto get(@PathVariable String identifier) {
	return mapper.toDto(service.retrieve(identifier));
    }

    //@PostMapping
    //public abstract Dto create(@RequestBody T t);

    @PutMapping
    public T update(@RequestBody T provider) {
	return service.save(provider);
    }

    @DeleteMapping("/{identifier}")
    public void update(@PathVariable String identifier) {
	service.delete(identifier);
    }
}
