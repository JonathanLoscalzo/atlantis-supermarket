package com.atlantis.supermarket.business.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.atlantis.supermarket.business.client.useCases.CreateClientByUserUseCase;
import com.atlantis.supermarket.business.client.useCases.input.CreateClientByUser;
import com.atlantis.supermarket.business.user.UserService;
import com.atlantis.supermarket.core.client.Client;
import com.atlantis.supermarket.core.user.User;
import com.atlantis.supermarket.core.user.exceptions.UsernameNotFoundException;
import com.atlantis.supermarket.core.user.generator.SaveUser;
import com.atlantis.supermarket.infrastructure.client.ClientRepository;
import com.atlantis.supermarket.infrastructure.user.UserRepository;

@RunWith(SpringRunner.class)
public class ClientServiceTest {

    @TestConfiguration
    static class MyTestConfiguration {

	@Bean
	public ClientService clientService(ClientRepository clients) {
	    return new ClientService(clients);
	}

	@Bean
	public UserService userService() {
	    return new UserService();
	}

	@Bean
	public CreateClientByUserUseCase createClientByUserUseCase() {
	    return new CreateClientByUserUseCase();
	}
    }

    @MockBean
    private UserRepository users;

    @MockBean
    private SaveUser saver;

    @MockBean
    private ClientRepository clients;

    @Autowired
    private ClientService service;

    @Test
    public void whenUserExistSaveClient() {
	// given
	User user = new User();
	user.setPassword("asdf");
	user.setUsername("asdf");
	saver.save(user);
	Mockito.when(users.findById(user.getId())).thenReturn(Optional.of(user));
	Mockito.when(clients.save(Mockito.any())).thenReturn(new Client());

	// when
	CreateClientByUser model = new CreateClientByUser();
	model.setDocument(12345).setName("name").setSurname("surname").setUserID(user.getId());
	Client client = null;

	try {
	    client = service.createClientByUser(model);
	} catch (UsernameNotFoundException e) {

	}

	// then
	assertThat(client).isNotNull();
	Mockito.verify(clients).save(Mockito.any());

    }

    @Test(expected = UsernameNotFoundException.class)
    public void whenUserNotExistThrow() throws UsernameNotFoundException {
	// given

	Mockito.when(users.findById(Mockito.any())).thenReturn(Optional.empty());

	// when
	CreateClientByUser model = new CreateClientByUser();
	model.setDocument(12345).setName("name").setSurname("surname").setUserID(UUID.randomUUID());

	service.createClientByUser(model);
    }

}
