package com.atlantis.supermarket.api.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atlantis.supermarket.business.product.ProductService;
import com.atlantis.supermarket.business.product.useCases.createProduct.CreateProduct;
import com.atlantis.supermarket.business.product.useCases.createProduct.UpdateProduct;
import com.atlantis.supermarket.core.product.dto.ProductDto;
import com.atlantis.supermarket.core.product.mapper.ProductMapper;
import com.atlantis.supermarket.infrastructure.product.ProductRepository;

@RestController
@RequestMapping("/api/product/batch")
public class BatchController {
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
	return productRepo.findAll(pageable).map(x -> productMapper.toDtoAllBatches(x));
    }

}
