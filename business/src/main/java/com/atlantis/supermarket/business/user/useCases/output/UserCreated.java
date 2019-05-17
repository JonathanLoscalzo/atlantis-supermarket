package com.atlantis.supermarket.business.user.useCases.output;

import com.atlantis.supermarket.core.shared.business.OutputPort;

public class UserCreated extends OutputPort {
    private String username;

    public UserCreated(String username2) {
	this.setUsername(username);
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }
}
