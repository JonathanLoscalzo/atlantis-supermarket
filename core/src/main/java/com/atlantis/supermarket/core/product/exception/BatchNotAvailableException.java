package com.atlantis.supermarket.core.product.exception;

public class BatchNotAvailableException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String batch;
    
    public BatchNotAvailableException(String uuid) {
	this.setBatch(uuid);
    }

    public String getBatch() {
	return batch;
    }

    public void setBatch(String batch) {
	this.batch = batch;
    }

}