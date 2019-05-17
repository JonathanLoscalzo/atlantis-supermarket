package com.atlantis.supermarket.core.user.event;

import java.util.UUID;

import com.atlantis.supermarket.core.shared.BaseEvent;

public class UserCreatedEvent extends BaseEvent {
    private UUID userId;

    public UserCreatedEvent(UUID id) {
	this.setUserId(id);
    }

    public UUID getUserId() {
	return userId;
    }

    public void setUserId(UUID userId) {
	this.userId = userId;
    }
}
