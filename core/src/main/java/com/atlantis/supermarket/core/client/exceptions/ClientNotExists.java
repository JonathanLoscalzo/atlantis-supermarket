package com.atlantis.supermarket.core.client.exceptions;

import java.util.UUID;

public class ClientNotExists extends RuntimeException {

    private String id;
    public ClientNotExists(UUID id) {
	// TODO Auto-generated constructor stub
	this.id = id.toString();
    }

    private static final long serialVersionUID = 1L;

}
