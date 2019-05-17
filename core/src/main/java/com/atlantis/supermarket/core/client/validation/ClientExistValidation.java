package com.atlantis.supermarket.core.client.validation;

import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.client.Client;
import com.atlantis.supermarket.core.client.exceptions.ClientNotExists;

@Component
public class ClientExistValidation {

    public void validate(Client client) throws ClientNotExists {
	if (client == null) {
	    throw new ClientNotExists(client.getId());
	}
    }
}
