package com.atlantis.supermarket.api.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atlantis.supermarket.business.product.ProductService;
import com.atlantis.supermarket.business.product.useCases.createProduct.*;
import com.atlantis.supermarket.core.product.dto.ProductDto;
import com.atlantis.supermarket.core.product.mapper.ProductMapper;
import com.atlantis.supermarket.core.user.User;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    
    @Autowired
    private CreateProduct createProduct;
    
    @Autowired private ProductService productService;
    
    @Autowired private ProductMapper productMapper;
    
    @GetMapping()
    public List<ProductDto> getProducts(){
	return productService.find().stream().map(x -> productMapper.toDto(x)).collect(Collectors.toList());
    }
    
    @PostMapping()
    public ProductDto createProduct(CreateProductInput input){
	CreateProductOutput output = createProduct.handle(input);
	return productMapper.toDto(output.getProduct());
    }
    
    @PutMapping()
    public ProductDto updateProduct(CreateProductInput input){
	CreateProductOutput output = createProduct.handle(input);
	return productMapper.toDto(output.getProduct());
    }
}
