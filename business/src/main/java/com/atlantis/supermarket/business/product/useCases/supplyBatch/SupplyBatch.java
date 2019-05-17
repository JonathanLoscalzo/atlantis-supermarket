package com.atlantis.supermarket.business.product.useCases.supplyBatch;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.core.product.exception.BatchNotExistException;
import com.atlantis.supermarket.core.shared.business.UseCaseInput;
import com.atlantis.supermarket.infrastructure.product.BatchRepository;

/**
 * for supply a batch
 * 
 * @author jloscalzo
 *
 */
@Service
public class SupplyBatch implements UseCaseInput<SupplyBatchInput> {

    @Autowired
    private BatchRepository batches;

    @Override
    @Transactional
    public void handle(SupplyBatchInput input) {

	Batch batch = batches.findById(input.batchId)
		.orElseThrow(() -> new BatchNotExistException(input.batchId.toString()));

	batch.supply(input.quantity);

	batches.save(batch);
	return;
    }

}
