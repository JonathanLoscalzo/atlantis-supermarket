package com.atlantis.supermarket.core.shared;

public abstract class BaseServiceEventHandler<T extends BaseEvent> {
    public abstract void handle(T event);
}
