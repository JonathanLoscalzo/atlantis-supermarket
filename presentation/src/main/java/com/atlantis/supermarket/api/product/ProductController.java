package com.atlantis.supermarket.api.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atlantis.supermarket.business.product.ProductService;
import com.atlantis.supermarket.business.product.useCases.createProduct.*;
import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.Provider;
import com.atlantis.supermarket.core.product.dto.ProductDto;
import com.atlantis.supermarket.core.product.mapper.ProductMapper;
import com.atlantis.supermarket.core.product.search.SearchProduct;
import com.atlantis.supermarket.core.user.User;
import com.atlantis.supermarket.infrastructure.product.ProductRepository;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private CreateProduct createProduct;
    @Autowired
    private UpdateProduct updateProduct;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepo; // todo: eliminar
    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public Page<ProductDto> get(Pageable pageable) {
	return productRepo.findAll(pageable).map(x -> productMapper.toDto(x));
    }
    
    @PostMapping("/search")
    public Page<ProductDto> getPagedWithSearch(@RequestBody SearchProduct search, Pageable pageable) {
	return productService.findByPattern(search, pageable);
    }

    @GetMapping("/{identifier}")
    public ProductDto get(@PathVariable String identifier) {
	return productMapper.toDto(productService.retrieve(identifier));
    }

    @PostMapping()
    public ProductDto createProduct(@RequestBody CreateProductInput input) {
	CreateProductOutput output = createProduct.handle(input);
	return productMapper.toDto(output.getProduct());
    }

    @PutMapping()
    public ProductDto updateProduct(@RequestBody CreateProductInput input) {
	CreateProductOutput output = updateProduct.handle(input);
	return productMapper.toDto(output.getProduct());
    }

    @DeleteMapping("/{identifier}")
    public void delete(@PathVariable String identifier) {
	productService.delete(identifier);
    }
}
