package com.atlantis.supermarket.api.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atlantis.supermarket.business.product.BatchService;
import com.atlantis.supermarket.business.product.ProductService;
import com.atlantis.supermarket.business.product.useCases.addBatch.AddBatch;
import com.atlantis.supermarket.business.product.useCases.addBatch.AddBatchInput;
import com.atlantis.supermarket.business.product.useCases.createProduct.CreateProduct;
import com.atlantis.supermarket.business.product.useCases.createProduct.UpdateProduct;
import com.atlantis.supermarket.business.product.useCases.editBatch.EditBatch;
import com.atlantis.supermarket.business.product.useCases.editBatch.EditBatchInput;
import com.atlantis.supermarket.business.product.useCases.supplyBatch.SupplyBatch;
import com.atlantis.supermarket.business.product.useCases.supplyBatch.SupplyBatchInput;
import com.atlantis.supermarket.core.product.dto.BatchDto;
import com.atlantis.supermarket.core.product.dto.ProductDto;
import com.atlantis.supermarket.core.product.mapper.BatchMapper;
import com.atlantis.supermarket.core.product.mapper.ProductMapper;
import com.atlantis.supermarket.infrastructure.product.ProductRepository;

@RestController
@RequestMapping("/api/product/batch")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @Autowired
    private ProductRepository productRepo; // todo: eliminar
    
    @Autowired
    private ProductMapper productMapper;
    
    @Autowired
    private BatchMapper batchMapper;
    
    @Autowired
    private SupplyBatch supplyBatch;
    
    @Autowired 
    private AddBatch addBatch;
    
    @Autowired
    private EditBatch editBatch;

    @GetMapping
    public Page<ProductDto> get(Pageable pageable) {
	return productRepo.findAll(pageable).map(x -> productMapper.toDtoAllBatches(x));
    }

    @GetMapping("/{identifier}")
    public BatchDto get(@PathVariable String identifier) {
	return batchMapper.toDtoWithProduct(batchService.retrieve(identifier));
    }
    
    @PutMapping("/{identifier}")
    public void supply(@RequestBody SupplyBatchInput input) {
	supplyBatch.handle(input);
    }
    
    @PostMapping()
    public void create(@RequestBody AddBatchInput input) {
	addBatch.handle(input);
    }
    

    @PutMapping("/edit")
    public void edit(@RequestBody BatchDto input) {
	editBatch.handle(new EditBatchInput(input));
    }
}
