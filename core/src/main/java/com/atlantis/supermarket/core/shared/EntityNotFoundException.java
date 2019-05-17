package com.atlantis.supermarket.core.shared;

public class EntityNotFoundException extends RuntimeException {

    public String msg;
    public EntityNotFoundException(String msg) {
	this.msg = msg;
    }

    private static final long serialVersionUID = 1L;

}
