package com.atlantis.supermarket.business.client.useCases;

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

@Service
public class CreateClientByUserUseCase /*implements UseCaseOutput<ClientCreated, CreateClientByUser>*/ {

    @Autowired
    private UserService userUseCase;

    @Autowired
    private ClientRepository clients;
    
    public ClientCreated handle(CreateClientByUser model) throws UsernameNotFoundException {

	User user = userUseCase.findById(model.userID);

	Client client = Client.createClientFromUser(user, model.getName(), model.getSurname(), model.getDocument());

	clients.save(client);

	return new ClientCreated(client);
    }

}
