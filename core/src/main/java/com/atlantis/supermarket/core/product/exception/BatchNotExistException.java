package com.atlantis.supermarket.core.product.exception;

public class BatchNotExistException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String batch;
    
    public BatchNotExistException(String uuid) {
	this.setBatch(uuid);
    }

    public String getBatch() {
	return batch;
    }

    public void setBatch(String batch) {
	this.batch = batch;
    }

}
