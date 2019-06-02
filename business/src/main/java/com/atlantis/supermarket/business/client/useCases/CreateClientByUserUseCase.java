package com.atlantis.supermarket.business.client.useCases;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlantis.supermarket.business.client.useCases.input.CreateClientByUser;
import com.atlantis.supermarket.business.client.useCases.output.ClientCreated;
import com.atlantis.supermarket.business.user.UserService;
import com.atlantis.supermarket.core.client.Client;
import com.atlantis.supermarket.core.shared.business.InputPort;
import com.atlantis.supermarket.core.shared.business.OutputPort;
import com.atlantis.supermarket.core.shared.business.UseCaseOutput;
import com.atlantis.supermarket.core.user.User;
import com.atlantis.supermarket.core.user.exceptions.UsernameNotFoundException;
import com.atlantis.supermarket.infrastructure.client.ClientRepository;
import com.atlantis.supermarket.infrastructure.user.UserRepository;

@Service
public class CreateClientByUserUseCase /* implements UseCaseOutput<ClientCreated, CreateClientByUser> */ {

    @Autowired
    private UserService userUseCase;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ClientRepository clients;

    @Transactional
    public ClientCreated handle(CreateClientByUser model) throws UsernameNotFoundException {
	
	User user = null;
	if (model.userID != null) {
	    user = userUseCase.findById(model.userID);
	} else {
	    user = userUseCase.findUserByUsername(model.getUsername());
	}
	
	Client client = null;
	
	client = clients.findOneByUserUsername(user.getUsername()).orElse(null);
	
	if (client == null) {
	    client = Client.createClientFromUser(user, model.getName(), model.getSurname(), model.getDocument());
	} else {
	    // Actúa como un update en caso que el cliente exista.
	    client.setDocument(model.getDocument());
	    client.setName(model.getName());
	    client.setSurname(model.getSurname());
	}
	
	user.setClient(client);
	user.setEmail(model.getEmail());
	clients.save(client);

	return new ClientCreated(client);
    }

}
