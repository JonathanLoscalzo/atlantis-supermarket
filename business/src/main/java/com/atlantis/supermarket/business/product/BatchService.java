package com.atlantis.supermarket.business.product;

import com.atlantis.supermarket.business.ServiceImpl;
import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.infrastructure.product.BatchRepository;

public class BatchService extends ServiceImpl<Batch>{

    protected BatchService(BatchRepository repo) {
	super(repo);
    }

}
