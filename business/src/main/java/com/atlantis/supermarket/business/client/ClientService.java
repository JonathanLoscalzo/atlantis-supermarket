package com.atlantis.supermarket.business.client;

import com.atlantis.supermarket.core.shared.BaseService;
import com.atlantis.supermarket.core.shared.business.UseCase;
import com.atlantis.supermarket.core.user.User;
import com.atlantis.supermarket.core.user.exceptions.UserExistsException;
import com.atlantis.supermarket.core.user.exceptions.UsernameNotFoundException;
import com.atlantis.supermarket.infrastructure.client.ClientRepository;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlantis.supermarket.business.ServiceImpl;
import com.atlantis.supermarket.business.client.useCases.CreateClientByUserUseCase;
import com.atlantis.supermarket.business.client.useCases.input.CreateClientAndUser;
import com.atlantis.supermarket.business.client.useCases.input.CreateClientByUser;
import com.atlantis.supermarket.business.user.UserService;
import com.atlantis.supermarket.core.client.Client;

@Service
public class ClientService extends ServiceImpl<Client> {

    @Autowired
    private ClientRepository clients;

    @Autowired
    private UserService userUseCase;
    
    @Autowired
    private CreateClientByUserUseCase createClientByUserUseCase;
    
    public ClientService(ClientRepository clients) {
	super(clients);
    }
    
    public Client createClientByUser(CreateClientByUser input) throws UsernameNotFoundException {
	return createClientByUserUseCase.handle(input).client;
    }
    
    @Transactional
    public Client createClientAndUser(CreateClientAndUser model) throws UsernameNotFoundException, UserExistsException {

	User user = userUseCase.createUser(model.getUsername(), model.getPassword());

	Client client = Client.createClientFromUser(user, model.getName(), model.getSurname(), model.getDocument());
	user.setEmail(model.getEmail());
	
	clients.save(client);

	return client;
    }

    public Client save(Client entity) {
	return this.clients.save(entity);
    }

    public Boolean exist(UUID id) {
	return this.clients.existsById(id);
    }

    public Client retrieve(UUID identifier) {
	return this.clients.getOne(identifier);
    }

    public void delete(UUID identifier) {
	this.clients.deleteById(identifier);
    }

}
