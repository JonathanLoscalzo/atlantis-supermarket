package com.atlantis.supermarket.business.product.useCases.editBatch;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.core.product.Product;
import com.atlantis.supermarket.core.product.exception.ProductNotFoundException;
import com.atlantis.supermarket.core.shared.EntityNotFoundException;
import com.atlantis.supermarket.core.shared.business.UseCaseInput;
import com.atlantis.supermarket.infrastructure.product.BatchRepository;
import com.atlantis.supermarket.infrastructure.product.ProductRepository;

@Service
public class EditBatch implements UseCaseInput<EditBatchInput> {

    @Autowired
    private ProductRepository products;

    @Autowired
    private BatchRepository batches;

    @Override
    public void handle(EditBatchInput input) {
	Product p = products.findById(UUID.fromString(input.batch.getProduct().getId()))
		.orElseThrow(() -> new ProductNotFoundException(UUID.fromString(input.batch.getProduct().getId())));

	Batch b = batches.findById(UUID.fromString(input.batch.getId()))
		.orElseThrow(
			() -> new EntityNotFoundException("no se encuentra", "Lote", input.batch.getProduct().getId()))
		.setDetail(input.batch.getDetail())
		.setExpiration(input.batch.getExpiration())
		.setRemainingUnits(input.batch.getRemainingUnits())
		.setProduct(p);
	p.changeNotified();
	batches.save(b);
	

    }

}
