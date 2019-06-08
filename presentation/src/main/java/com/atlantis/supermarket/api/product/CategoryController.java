package com.atlantis.supermarket.api.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atlantis.supermarket.api.shared.BaseController;
import com.atlantis.supermarket.business.product.CategoryService;
import com.atlantis.supermarket.core.product.Category;
import com.atlantis.supermarket.core.product.dto.CategoryDto;
import com.atlantis.supermarket.core.product.mapper.CategoryMapper;
import com.atlantis.supermarket.core.shared.BaseEntity;
import com.atlantis.supermarket.infrastructure.product.CategoryRepository;

@RestController
@RequestMapping("/api/product/category")
public class CategoryController {

    @Autowired
    private CategoryService service;
    @Autowired
    private CategoryRepository repo;
    @Autowired
    private CategoryMapper mapper;

    @PostMapping
    public CategoryDto create(@RequestBody Category c) {
	return mapper.toDto(service.save(c));
    }
    
    @GetMapping
    public Page<CategoryDto> get(Pageable pageable) {
	return repo.findAll(pageable).map(mapper::toDto);
    }

    @GetMapping("/all")
    public List<CategoryDto> get() {
	return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{identifier}")
    public CategoryDto get(@PathVariable String identifier) {
	return mapper.toDto(service.retrieve(identifier));
    }

    @PutMapping
    public CategoryDto update(@RequestBody Category provider) {
	return mapper.toDto(service.save(provider));
    }

    @DeleteMapping("/{identifier}")
    public void update(@PathVariable String identifier) {
	service.delete(identifier);
    }
}
