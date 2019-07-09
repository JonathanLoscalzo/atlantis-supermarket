package com.atlantis.supermarket.core.client.factories;

import org.springframework.stereotype.Component;

import com.atlantis.supermarket.core.client.Client;
import com.atlantis.supermarket.core.user.User;

@Component
public class ClientFactory {
    public static Client defaultClient(User user) {
	Client c = Client.createClientFromUser(user, "","",null);
	return c;
    }
}
