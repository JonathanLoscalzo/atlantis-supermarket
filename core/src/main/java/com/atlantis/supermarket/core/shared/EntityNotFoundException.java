package com.atlantis.supermarket.core.shared;

public class EntityNotFoundException extends RuntimeException {

    public String msg;
    public String entity;
    public String param;
    
    public EntityNotFoundException(String msg, String entity, String param) {
	this.msg = msg;
	this.entity = entity;
	this.param = param;
    }

    private static final long serialVersionUID = 1L;

}
