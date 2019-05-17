package com.atlantis.supermarket.core.shared.business;

public interface UseCaseInput<T extends InputPort> {
    public void handle(T input);
}
