package com.atlantis.supermarket.business.product.useCases.editBatch;

import java.util.Date;
import java.util.UUID;

import com.atlantis.supermarket.core.product.dto.BatchDto;
import com.atlantis.supermarket.core.shared.business.InputPort;

public class EditBatchInput extends InputPort {

    public BatchDto batch;

    public EditBatchInput(BatchDto batch) {
	this.batch = batch;
    }
}
