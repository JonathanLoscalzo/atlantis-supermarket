package com.atlantis.supermarket.business.client.useCases.output;

import com.atlantis.supermarket.core.client.Client;
import com.atlantis.supermarket.core.shared.business.OutputPort;

public class ClientCreated extends OutputPort {

    public Client client;
    
    public ClientCreated(Client client) {
	this.client = client;
    }
}
