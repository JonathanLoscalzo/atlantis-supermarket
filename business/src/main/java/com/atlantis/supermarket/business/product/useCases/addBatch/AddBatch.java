package com.atlantis.supermarket.business.product.useCases.addBatch;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.exception.ProductNotFoundException;
import com.atlantis.supermarket.core.shared.business.UseCaseInput;
import com.atlantis.supermarket.infrastructure.product.ProductRepository;

@Service
public class AddBatch implements UseCaseInput<AddBatchInput>{

    @Autowired
    private ProductRepository products;
    
    @Override
    public void handle(AddBatchInput input) {
	Product p = products.findById(input.productId).orElseThrow( ()->new ProductNotFoundException(input.productId));
	
	Batch b = new Batch()
		.setDetail(input.detail)
		.setEntry(new Date())
		.setExpiration(input.expiration)
		.setRemainingUnits(input.remainingUnits)
		.setProducto(p);
		
	p.addBatch(b);
	
	products.save(p);
	
    }

}
