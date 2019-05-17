package com.atlantis.supermarket.core.product.events;

import com.atlantis.supermarket.core.product.Batch;
import com.atlantis.supermarket.core.shared.BaseEvent;

public class BatchEmptyEvent extends BaseEvent {

    private Batch batch;

    public BatchEmptyEvent(Batch batch) {
	this.setBatch(batch);
    }

    public Batch getBatch() {
	return batch;
    }

    public void setBatch(Batch batch) {
	this.batch = batch;
    }

}
